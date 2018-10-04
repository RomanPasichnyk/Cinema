import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Cinema implements Serializable {
    private Set<Film> films;
    private Set<Session> sessions;

    public Cinema() {
        films = new HashSet<>();
        sessions = new HashSet<>();
    }

    public Set<Film> getFilms() {
        return films;
    }

    public Set<Session> getSessions() {
        return sessions;
    }
}
