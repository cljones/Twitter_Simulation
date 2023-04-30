import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;

public class TwitterDriver {

	/**
	 * @param args
	 * @throws IOException
	 * @throws CloneNotSupportedException
	 */

	static ArrayList<TwitterUser> twitter_byPop = new ArrayList<>();

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader read = new BufferedReader(new FileReader("src/social_network.edgelist"));

		ArrayList<TwitterUser> twitter = new ArrayList<>(); // Container for all

		ArrayList<Integer> intList = new ArrayList<>(); // List for all the
														// integers
		TwitterUser twUser = null;
		TwitterUser twFollower = null;
		int t1 = 0;
		int t2 = 0;

		try {
			while (true) {
				// For creating and setting up the objects
				String str = read.readLine();
				String[] tokens = str.split(" ");

				t1 = Integer.parseInt(tokens[0]);
				t2 = Integer.parseInt(tokens[1]);

				// For t1
				if (!intList.contains(t1)) {
					intList.add(t1);
					twUser = new TwitterUser(t1);
					twitter.add(twUser);
					twitter_byPop.add(twUser);
				} else {
					for (TwitterUser t : twitter) {
						if (t.getUsrID() == t1) {
							twUser = t;
							break;
						}
					} // End of for each loop
				} // End of else

				// For t2
				if (!intList.contains(t2)) {
					intList.add(t2);
					twFollower = new TwitterUser(t2);
					twitter.add(twFollower);
					twitter_byPop.add(twFollower);
				} else {
					for (TwitterUser t : twitter) {
						if (t.getUsrID() == t2) {
							twFollower = t;
							break;
						}
					} // End of for each loop
				} // End of else

				twUser.addFollow(twFollower);
				twFollower.addFollowing(twUser);

			} // End of while loop

		} catch (Exception e) {

		} finally {
			read.close();
		}

		// Recursive call
		System.out.println("Recursive Call");

		TwitterUser tu = twitter.get(1);
		for (TwitterUser t : twitter) {
			System.out.println(tu.getNeighborhood(t, 6));
			break;
		}
		System.out.println();
		// Deep copy method
		System.out.println("Deep Copy Method");
		System.out.println(tu.clone());
		System.out.println();

		// The get following
		System.out.println("The get following method");
		System.out.println("The followers of user " + tu + "\n" + tu.getFollowing());
		System.out.println();

		// Get popularity
		System.out.println("Get the popularity");

		Collections.sort(twitter_byPop, new TCompare());

		System.out.println("The Twitter User ordered by popularity: " + twitter_byPop);
		System.out.println("The xth popular user is " + getPopularity(0));

	}// End of Main Method

	public static TwitterUser getPopularity(int x) {

		return twitter_byPop.get(x);
	}
}// End of Class
