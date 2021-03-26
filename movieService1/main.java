package movieService1;

import java.util.Arrays;

import movieService1.exceptions.MovieExistsException;
import movieService1.exceptions.UserExistsException;
import movieService1.models.*;
import movieService1.service.*;

public class main {

	static MovieService movieService = new MovieService();
	static UserService userService = new UserService();
	static UserLevelService userLevelService = new UserLevelService(userService);

	static ReviewService reviewService = new ReviewService(userService, movieService, userLevelService);

	public static void main(String[] args) throws Exception {

		System.out.println(" \n \n Adding Movies--- ");
		movieModel movie1 = movieService.AddMovie("DON", 2006, Arrays.asList("action", "comedy"));
		movieModel movie2 = movieService.AddMovie("Tiger", 2008, Arrays.asList("drama"));
		movieModel movie3 = movieService.AddMovie("Padmaavat", 2006, Arrays.asList("comedy"));
		movieModel movie4 = movieService.AddMovie("Lunchbox", 2021, Arrays.asList("drama"));
		movieModel movie5 = movieService.AddMovie("Guru", 2006, Arrays.asList("drama"));
		movieModel movie6 = movieService.AddMovie("Metro", 2006, Arrays.asList("romance"));

		System.out.println(" \n \n Adding users to the system:");
		userModel user1 = userService.AddUser("SRK");
		userModel user2 = userService.AddUser("Deepika");
		userModel user3 = userService.AddUser("Salman");

		System.out.println("Adding reviews \n \n");
		reviewService.add_review(user1.getName(), movie1.getName(), 2);
		reviewService.add_review(user1.getName(), movie3.getName(), 8);
		reviewService.add_review(user3.getName(), movie1.getName(), 5);
		reviewService.add_review(user2.getName(), movie1.getName(), 9);
		reviewService.add_review(user2.getName(), movie5.getName(), 6);
		// reviewService.add_review(user1.getName(), movie1.getName(), 10); // gives
		// expected exception
		reviewService.add_review(user2.getName(), movie4.getName(), 5);
		reviewService.add_review(user1.getName(), movie2.getName(), 5);
		reviewService.add_review(user1.getName(), movie6.getName(), 7);
		reviewService.add_review(user1.getName(), movie5.getName(), 7);

		System.out.println("\n Top N Movies in genre req:");
		System.out.println(reviewService.getTopNMoviesByCriticsInGenre(3, "critic", "drama").toString());

		System.out.println("\n average score in year ");
		System.out.println(reviewService.getAverageReviewScoreInYear("2006"));

		System.out.println("\n average score for movie ");
		System.out.println(reviewService.getAverageReviewScoreForMovie("Tiger"));

	}

}
