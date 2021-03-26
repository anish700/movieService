package movieService1.exceptions;

import movieService1.models.movieModel;

public class AlreadyReviewedException extends Exception {
    public AlreadyReviewedException(String message) {
        super(message);
    }
}
