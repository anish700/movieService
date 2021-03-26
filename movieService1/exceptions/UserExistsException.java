package movieService1.exceptions;

import movieService1.models.movieModel;

public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}
