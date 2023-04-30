import java.util.Comparator;


public class TCompare implements Comparator<TwitterUser>{

		@Override
		public int compare(TwitterUser arg0, TwitterUser arg1) {
			if (arg0.getFollowed().size() < arg1.getFollowed().size()) {
				return 1;
			} else if (arg0.getFollowed().size() > arg1.getFollowed().size()) {
				return -1;
			} else if (arg0.getFollowing().size() < arg1.getFollowing().size()) {
				return 1;
			} else if (arg0.getFollowing().size() > arg1.getFollowing().size()) {
				return -1;
			}else{
			return 0;
			}
		}

	
}
