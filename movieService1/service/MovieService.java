package movieService1.service;

import java.util.Map;
import java.util.stream.Collectors;
import movieService1.exceptions.MovieExistsException;
import movieService1.models.movieModel;
import java.util.HashMap;
import java.util.List;


public class MovieService {
    private static final Map<String, movieModel> movies = new HashMap<>(); 

    public void checkNewMovie(String movieName) throws MovieExistsException{
     if (movies.containsKey(movieName)){
         throw new MovieExistsException("movie is already existing");
     }
    }

    public void checkMovieExists(String movieName) throws MovieExistsException{
        if (!movies.containsKey(movieName)){
            throw new MovieExistsException("movie does not exist");
        }
       }

       // -------- ADD MOVIE ------------
    public movieModel AddMovie(String movieName,int year,List<String> genre) throws MovieExistsException{
     this.checkNewMovie(movieName);
     movieModel movie=new movieModel(movieName, year, genre);
     movies.put(movieName, movie);
     System.out.println(movies);
     return movie;
    }

    public List<movieModel> getMoviesByGenre(String genre) {
        List<movieModel> moviesByGenre = movies.values()
                .stream()
                .filter((movie) -> movie.getGenre()
                        .contains(genre))
                .collect(Collectors.toList());
        return moviesByGenre;
    }

    public List<movieModel> getMoviesByReleaseYear(String year) {
        List<movieModel> moviesByYear = movies.values()
                .stream()
                .filter(movie -> year.equals(String.valueOf(movie.getYear())))
  
                .collect(Collectors.toList());
        return moviesByYear;
    }
    public movieModel getMovieFromName(String movieName) throws MovieExistsException {
        this.checkMovieExists(movieName);
        return movies.get(movieName);

       }
}
