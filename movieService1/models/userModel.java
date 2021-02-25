package movieService1.models;

public class userModel {
    private String userName;
    private int userID;
    private int reviewsCount=0;
    private String userLevel ; // critic/viewer etc

    public userModel(String userName, String userLevel) {

        this.userName = userName;
        this.userLevel = userLevel;
    }

    public void add_review() {
        this.reviewsCount++;
    }
    public String getUserLevel() {
        return this.userLevel;
    }

	public String getName() {
        return this.userName;
	}

	public Integer getReviewsCount() {
		return this.reviewsCount;
	}

	public void setUserLevel(String nextLevel) {
        this.userLevel=nextLevel;
	}
	

}
