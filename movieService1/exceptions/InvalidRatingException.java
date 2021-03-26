package movieService1.exceptions;

import movieService1.models.movieModel;

public class InvalidRatingException extends Exception {
    public InvalidRatingException(String message) {
        super(message);
    }
}