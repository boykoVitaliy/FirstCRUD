package app.service;

import app.model.Film;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface FilmService {

    List<Film> allFilms(int page);
    void add(Film film);
    void delete (Film film);
    void edit (Film film);
    Film getById(int id);

    int filmsCount();

    boolean checkTitle(String title);

}
