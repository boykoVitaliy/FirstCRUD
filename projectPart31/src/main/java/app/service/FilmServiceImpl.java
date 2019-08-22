package app.service;

import app.dao.FilmDAO;
import app.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService{

    @Autowired
    private FilmDAO filmDAO ;

    @Transactional
    @Override
    public List<Film> allFilms(int page) {
        return filmDAO.allFilms(page);

    }

    @Transactional
    @Override
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Transactional
    @Override
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Transactional
    @Override
    public void edit(Film film) {
        filmDAO.edit(film);
    }

    @Transactional
    @Override
    public Film getById(int id) {
        return filmDAO.getById(id);
    }

    @Override
    @Transactional
    public int filmsCount() {
        return filmDAO.filmsCount();
    }

    @Override
    @Transactional
    public boolean checkTitle(String title) {
        return filmDAO.checkTitle(title);
    }
}
