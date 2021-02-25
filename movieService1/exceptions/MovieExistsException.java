package movieService1.exceptions;
import  movieService1.models.movieModel;

public class MovieExistsException extends Exception{

    public MovieExistsException(String message) {
        super(message);
    }
}
