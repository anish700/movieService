
package movieService1.exceptions;

import movieService1.models.movieModel;

public class UserLevelNotFoundException extends Exception {
    public UserLevelNotFoundException(String message) {
        super(message);
    }
}
