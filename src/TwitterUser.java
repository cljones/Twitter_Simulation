import java.util.ArrayList;

/**
 * 
 */

public class TwitterUser implements Cloneable, Comparable<TwitterUser> {

	private int usrID;
	private ArrayList<TwitterUser> followed;
	private ArrayList<TwitterUser> following;

	public TwitterUser(int usrID) {

		this.usrID = usrID;
		followed = new ArrayList<>();
		following = new ArrayList<>();
	}

	/**
	 * @return the usrID
	 */
	public int getUsrID() {
		return usrID;
	}

	/**
	 * @param usrID
	 *            the usrID to set
	 */
	public void setUsrID(int usrID) {
		this.usrID = usrID;
	}

	public void addFollow(TwitterUser tw) {

		this.followed.add(tw);
	}

	public void addFollowing(TwitterUser twFollow) {

		this.following.add(twFollow);
	}

	/**
	 * @return the followed
	 */
	public ArrayList<TwitterUser> getFollowed() {
		return followed;
	}

	public ArrayList<TwitterUser> getFollowing() {
		return following;
	}

	/**
	 * @param followed
	 *            the followed to set
	 */
	public void setFollowed(ArrayList<TwitterUser> followed) {
		this.followed = followed;
	}

	public ArrayList<TwitterUser> getNeighborhood(TwitterUser usr, int depth) {

		ArrayList<TwitterUser> twList = new ArrayList<>();
		if (depth > 0) {

			twList = getNeighborhood(usr, twList, depth);
			depth--;

		}
		return twList;

	}

	private ArrayList<TwitterUser> getNeighborhood(TwitterUser twUsr, ArrayList<TwitterUser> tList, int depth) {
		if (depth > 0) {
			if (twUsr.getFollowed().isEmpty()) {
				return tList;
			} else {

				for (TwitterUser w : twUsr.getFollowed()) {
					if (!tList.contains(w)) {
						tList.add(w);
						getNeighborhood(w, tList, depth - 1);

					} else {
						getNeighborhood(w, tList, depth - 1);

					}
				}
			}
		}
		return tList;
	}// End of private method

	public Object clone() throws CloneNotSupportedException {

		TwitterUser twClone = new TwitterUser(this.usrID);
		// Fresh copy of the twitter followers
		for (TwitterUser tw : this.getFollowed()) {
			twClone.addFollow(tw);

		}
		System.out.println("Followers: " + twClone.getFollowed());
		System.out.print("Twitter UserID: ");
		return twClone;

	}

	@Override
	public int compareTo(TwitterUser o) {

		return this.getUsrID() > o.getUsrID() ? 1 : this.getUsrID() < o.getUsrID() ? -1 : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + usrID;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwitterUser other = (TwitterUser) obj;
		if (usrID != other.usrID)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + usrID;
	}

}// End of Twitter User class
