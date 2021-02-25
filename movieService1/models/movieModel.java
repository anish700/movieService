package movieService1.models;
import java.util.List;

public class movieModel {
private String name;
private int yearReleased ;
private List<String> genre;   

   public movieModel(String name , int yearReleased , List<String> genre ){
       this.name=name;
       this.yearReleased=yearReleased;
       this.genre=genre;

   }

public String getName() {
	return this.name;
}

public List<String> getGenre() {
	return this.genre;
}

public int getYear() {
	return this.yearReleased;
}

}