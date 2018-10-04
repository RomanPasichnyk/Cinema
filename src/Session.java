import java.io.Serializable;
import java.time.LocalDateTime;

public class Session implements Serializable {
    private Film film;
    private Hall hall;
    private LocalDateTime time;

    public Session(Film film, Hall hall, LocalDateTime time) {
        this.film = film;
        this.hall = hall;
        this.time = time;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Session{" +
                "film=" + film +
                ", hall=" + hall +
                ", time=" + time +
                '}';
    }
}
