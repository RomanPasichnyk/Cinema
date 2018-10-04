import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Program {
    private static Scanner sc = new Scanner(System.in);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Cinema cinema;
    private static Set<Film> films = new HashSet<>();
    private static Set<Session> sessions = new HashSet<>();

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        initCinema();

        System.out.println("Введіть логін");
        String dostup = sc.next();
        String answer = " ";

        if (dostup.equals("admin")) {
            while (!answer.equals("Вихід")) {
                if (!answer.equals("Вихід")) {
                    System.out.println();
                    System.out.println("[Admin] Введіть: || Вихід || ДодатиФільм || ДодатиСеанс || ВидалитиФільм || ВидалитиСеанс ||");
                    System.out.println("[Admin] Введіть: || ПереглядФільмів || ПереглядСеансів || Зберегти || Завантажити || ЗнайтиСеанс ||");
                    System.out.println();
                }
                answer = sc.next();
                switch (answer) {
                    case "ДодатиФільм":
                        addFilm(films);
                        break;
                    case "ДодатиСеанс":
                        addSession(sessions, films);
                        break;
                    case "ВидалитиФільм":
                        dellFilm(films);
                        break;
                    case "ВидалитиСеанс":
                        dellSession(sessions);
                        break;
                    case "ПереглядФільмів":
                        for (Film film : films) {
                            System.out.println(film);
                        }
                        break;
                    case "ПереглядСеансів":
                        for (Session session : sessions) {
                            System.out.println(session);
                        }
                        break;
                    case "Зберегти":
                        saveCinema(cinema);
                        cinema.getFilms().addAll(films);
                        cinema.getSessions().addAll(sessions);
                        break;
                    case "Завантажити":
                        initCinema();
                        break;
                    case "ЗнайтиСеанс":
                        System.out.println("[U] Введіть: || ЗаДнем || ЗаФільмом ||");
                        answer = sc.next();
                        boolean findSession = false;
                        if (answer.equals("ЗаФільмом")) {
                            System.out.println("Введіть назву фільма");
                            String nameFilm = null;
                            try {
                                nameFilm = br.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            for (Session session : sessions) {
                                if (session.getFilm().getName().equals(nameFilm)) {
                                    System.out.println(session);
                                    findSession = true;
                                }
                            }
                            if (!findSession) {
                                System.err.println("Сеансів не знайдено!");
                            }
                        } else if (answer.equals("ЗаДнем")) {
                            System.out.println("Введіть дату сеансу ( 2018.12.31 )");
                            String time = null;
                            try {
                                time = br.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            time = replaceSymbol(time);
                            time = time.replace('.', '-');
                            for (Session session : sessions) {
                                if (session.getTime().toString().startsWith(time)) {
                                    System.out.println(session);
                                    findSession = true;
                                }
                            }
                            if (!findSession) {
                                System.err.println("Сеансів не знайдено!");
                            }
                        }
                        break;
                }
            }

        } else if (dostup.equals("user")) {
            while (!answer.equals("Вихід")) {
                if (!answer.equals("Вихід")) {
                    System.out.println();
                    System.out.println("[User] Введіть: || ПереглядФільмів || ПереглядСеансів || ЗнайтиСеанс || Вихід ||");
                    System.out.println();
                }
                answer = sc.next();
                switch (answer) {
                    case "ЗнайтиСеанс":
                        System.out.println("[U] Введіть: || ЗаДнем || ЗаФільмом ||");
                        answer = sc.next();
                        boolean findSession = false;
                        if (answer.equals("ЗаФільмом")) {
                            System.out.println("Введіть назву фільма");
                            String nameFilm = null;
                            try {
                                nameFilm = br.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            for (Session session : sessions) {
                                if (session.getFilm().getName().equals(nameFilm)) {
                                    System.out.println(session);
                                    findSession = true;
                                }
                            }
                            if (!findSession) {
                                System.err.println("Сеансів не знайдено!");
                            }
                        } else if (answer.equals("ЗаДнем")) {
                            System.out.println("Введіть дату сеансу ( 2018.12.31 )");
                            String time = null;
                            try {
                                time = br.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            time = replaceSymbol(time);
                            time = time.replace('.', '-');
                            for (Session session : sessions) {
                                if (session.getTime().toString().startsWith(time)) {
                                    System.out.println(session);
                                    findSession = true;
                                }
                            }
                            if (!findSession) {
                                System.err.println("Сеансів не знайдено!");
                            }
                        }
                        break;
                    case "ПереглядФільмів":
                        for (Film film : films) {
                            System.out.println(film);
                        }
                        break;
                    case "ПереглядСеансів":
                        for (Session session : sessions) {
                            System.out.println(session);
                        }
                        break;
                }
            }
        }


    }

    private static void addFilm(Set<Film> films) {
        System.out.println("Введіть назву Фільму, який хочете добавити");
        String name = null;
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Введіть дату прем'єри ( 31.12.2018 )");
        String premiere = sc.next();
        premiere = replaceSymbol(premiere);

        Genre genre = null;
        boolean genreAc = false;
        while (!genreAc) {
            try {
                genreAc = true;
                System.out.println("Genre: Action, Romantic, Comedy, Adventures, Science, Fantasy, Historical, Drams, Thriller, Horrible");
                System.out.println("Введіть жанр фільму");
                String genreString = sc.next().toUpperCase();
                genre = Genre.valueOf(genreString);
            } catch (IllegalArgumentException e) {
                System.err.println("Некоректний ввід жанру!");
                genreAc = false;
            }
        }

        System.out.println("Введіть тривалість фільму в хвилинах");
        while (!sc.hasNextInt()) {
            System.out.println("Введіть тривалість фільму в хвилинах!");
            sc.next();
        }
        int movieLength = sc.nextInt();

        try {
            films.add(new Film(name, LocalDate.parse(premiere, DateTimeFormatter.ofPattern("dd.MM.yyyy").withLocale(Locale.US)), genre, movieLength));
            System.out.println("Фільм успішно добавлений!");
        } catch (DateTimeParseException e) {
            System.out.println("Фільм не добавлений.");
            System.err.println("Некоректний ввід дати!");
        }
    }

    private static void dellFilm(Set<Film> films) {
        System.out.println("Введіть ім'я фільму, який хочете видалити");
        String name = sc.next();
        boolean dell = false;
        Iterator<Film> iterator = films.iterator();
        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (film.getName().equals(name)) {
                iterator.remove();
                System.out.println("Фільм " + name + " успішно видалений.");
                System.out.println();
                dell = true;
            }
        }
        if (!dell) {
            System.err.println("Фільм не знайдено!");
        }
    }

    private static void addSession(Set<Session> sessions, Set<Film> films) {
        boolean filmFind = false;
        Film filmSes = null;
        System.out.println("Введіть назву Фільму, який хочете добавити в сеанс");
        String name = null;
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Film film : films) {
            if (name.equals(film.getName())) {
                filmFind = true;
                filmSes = film;
                break;
            }
        }
        if (!filmFind) {
            System.err.println("Фільм не знайдено!");
            return;
        }
        int numberHall;
        do {
            System.out.println("Введіть номер залу ( від 1 до 10 )");
            numberHall = sc.nextInt();
        } while (numberHall <= 0 || numberHall > 10);

        System.out.println("Введіть дату сеансу ( 31.12.2018 20:30 )");
        String time = null;
        try {
            time = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        time = replaceSymbol(time);
        try {
            sessions.add(new Session(filmSes, new Hall(numberHall), LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").withLocale(Locale.US))));
        } catch (DateTimeParseException e) {
            System.err.println("Некоректний ввід дати!");
        }
        System.out.println("Сеанс успішно добавлений!");
    }

    private static void dellSession(Set<Session> sessions) {
        System.out.println("Введіть ім'я фільму, з потрібного сеансу");
        String name = sc.next();
        System.out.println("Введіть номер залу, де проходить сеанс");
        int number = sc.nextInt();
        System.out.println("Введіть дату сеансу ( 31.12.2018 20:30 )");
        String time = null;
        try {
            time = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        time = replaceSymbol(time);
        boolean dell = false;
        Iterator<Session> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if (session.getFilm().getName().equals(name)) {
                if (session.getHall().getNumber() == number) {
                    try {
                        if (session.getTime().equals(LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").withLocale(Locale.US)))) {
                            iterator.remove();
                            dell = true;
                            System.out.println("Сеанс успішно видалений!");
                        }
                    } catch (DateTimeParseException e) {
                        System.err.println("Некоректний ввід дати!");
                    }
                }
            }
        }
        if (!dell) {
            System.err.println("Сеанс не знайдено!");
        }
    }

    private static String replaceSymbol(String time) {
        if (time.contains("/")) time = time.replace('/', '.');
        else if (time.contains("-")) time = time.replace('-', '.');
        else if (time.contains("_")) time = time.replace('_', '.');
        return time;
    }

    private static void initCinema() {
        File file = new File("Cinema");
        if (file.exists()) {
            try (
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                Object o = ois.readObject();
                if (o instanceof Cinema) {
                    cinema = (Cinema) o;
                    films.addAll(cinema.getFilms());
                    sessions.addAll(cinema.getSessions());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException e) {
                cinema = new Cinema();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            cinema = new Cinema();
        }
    }

    private static void saveCinema(Cinema cinema) {
        try (
                FileOutputStream fos = new FileOutputStream("Cinema");
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(cinema);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
