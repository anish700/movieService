package movieService1.models;

public class reviewModel {
    private String userName;
    private String movieName;
    private int rating ;
    private String userLevel;


    public reviewModel(String userName, String movieName, int rating, String userLevel) {
        this.userName = userName;
        this.movieName = movieName;
        this.rating = rating;
        this.userLevel = userLevel;
    }


	public String getMovieName() {
		return this.movieName;
	}


	public String getUserLevel() {
        System.out.println("  in reviewmodel : userlevel is  " +this.userLevel);
        return this.userLevel	;}


	public Integer getRating() {
        return this.rating;
       // return Integer.toString(rating1)
        
	}

}
