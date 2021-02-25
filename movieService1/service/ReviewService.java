package movieService1.service;

import movieService1.exceptions.AlreadyReviewedException;
import movieService1.exceptions.InvalidRatingException;
import movieService1.exceptions.MovieExistsException;
import movieService1.exceptions.UserExistsException;
import movieService1.models.movieModel;
import movieService1.models.reviewModel;
import movieService1.models.userModel;

import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ReviewService {

    private MovieService movieService;
    private UserService userService;
    private UserLevelService userLevelService;

    private final static Map<String, List<reviewModel>> movieReviews = new HashMap<>();
    private final static Map<String, List<reviewModel>> userReviews = new HashMap<>();

    public ReviewService(UserService userService, MovieService movieService, UserLevelService userLevelService) {
        this.userService = userService;
        this.movieService = movieService;
        this.userLevelService = userLevelService;
    }

    public void add_review(String userName, String movieName, int rating)
            throws Exception {
            userModel user = userService.getUserFromName(userName);
            movieModel movie = movieService.getMovieFromName(movieName);

            //checking if user already reviewed the same movie
            if (userReviews.containsKey(userName)) {
                boolean reviewAvl = userReviews.get(userName).stream()
                        .anyMatch(review -> movieName.equals(review.getMovieName()));
                if (reviewAvl) {
                    throw new AlreadyReviewedException("user has already reviewed the movie");
                }
            }
            if ((rating < 0) || (rating > 10))
            throw new InvalidRatingException("invalid rating , not in range 1-10");
            System.out.println(user.getUserLevel()+ " : current user level");
            Integer weight = userLevelService.getWeightage(user.getUserLevel());
            reviewModel review = new reviewModel(user.getName(),
                    movie.getName(),
                    rating * weight, // to make critic =2*viewer weight , etc
                    user.getUserLevel());
            this.addReviewToXMap(movieReviews, movieName, review);
            this.addReviewToXMap(userReviews, userName, review);
            this.userLevelService.userLevelUpdate(userName);
            System.out.println(" Review added ");
            System.out.println(userReviews);
            System.out.println(movieReviews);



          }

          private void addReviewToXMap(Map<String, List<reviewModel>> xMap, String key,
                  reviewModel review) {
                    List<reviewModel> reviews = new ArrayList<>();
                    if ( xMap.containsKey(key)) {
                        reviews = xMap.get(key);
                    }
                    reviews.add(review);
                    xMap.put(key, reviews);
          }

    public List<String> getTopNMoviesByCriticsInGenre(Integer N, 
          String userLevel, String genre) {
            //   userLevel="critic";
            //userModel user = userService.getUserFromName(userName);
            List<movieModel> moviesByGenre = this.movieService.getMoviesByGenre(genre);
            List<reviewModel> movies = this.getReviewByMovieNames(moviesByGenre);
            List<reviewModel> topReviews = movies.stream()
                    .filter(review -> userLevel.equals(review.getUserLevel())) // -----
                    .sorted((a, b) -> b.getRating().compareTo(a.getRating()))
                    .limit(N)
                    .collect(Collectors.toList());
            return topReviews.stream()
                    .map(review -> review.getMovieName())
                    .collect(Collectors.toList());
        }
          
        private List<reviewModel> getReviewByMovieNames(List<movieModel> movies) {
            return movies.stream()
                    .map(movie -> movieReviews.get(movie.getName()))
                    .filter(Objects::nonNull)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
    // ---- //

    // calc avg scores functions
    public Double getAverageReviewScoreInYear(String year) {
        System.out.println(year);
        List<movieModel> movies = movieService.getMoviesByReleaseYear(year);
        List<reviewModel> reviews = this.getReviewByMovieNames(movies);
        return this.getAverage(reviews);
    }

        public Double getAverageReviewScoreForMovie(String movieName) {
            List<reviewModel> reviews = movieReviews.get(movieName);
            return this.getAverage(reviews);
        }
// ------//

        private Double getAverage(List<reviewModel> reviews) {
            OptionalDouble average = reviews.stream().mapToDouble(reviewModel::getRating).average();
            return average.isPresent() ? average.getAsDouble() : 0.0;
        }

        

       
    

}
