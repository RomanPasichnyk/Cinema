import java.io.Serializable;
import java.time.LocalDate;

public class Film implements Serializable {
    private String name;
    private LocalDate premiere;
    private Genre genre;
    private int movieLength;

    public Film() {
    }

    public Film(String name, LocalDate premiere, Genre genre, int movieLength) {
        this.name = name;
        this.premiere = premiere;
        this.genre = genre;
        this.movieLength = movieLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPremiere() {
        return premiere;
    }

    public void setPremiere(LocalDate premiere) {
        this.premiere = premiere;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", premiere=" + premiere +
                ", genre=" + genre +
                ", movieLength=" + movieLength +
                '}';
    }
}
