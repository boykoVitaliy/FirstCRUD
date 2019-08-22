package app.controller;


import app.model.Film;
import app.service.FilmService;
import app.service.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// Клас який обробляє різні запроси

import java.util.List;



@Controller //<--каже Spring MVC що це контроллер
public class FilmController {

    private int pages;

    @Autowired
    private FilmService filmService ;

    @Autowired
    public void setFilmService(FilmService filmService) {

        this.filmService = filmService;
    }

// @RequestMapping - задає адресу методам контроллера , по яким вони будуть доступні в браузері.
//  для allFilms() парамеер value встановлений "/" , тому він буде відкриватись зразу
    @RequestMapping(value = "/",method = RequestMethod.GET)//<-method вказує який тип запросу підримується
    public ModelAndView allFilms(@RequestParam(defaultValue = "1")int page) {//<- ModelAndView вказуєм імя обєкта яке треба повернути
        List<Film> films = filmService.allFilms(page);
        int filmsCount = filmService.filmsCount();
        int pagesCount = (filmsCount+9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("page", page);
        modelAndView.addObject("filmsList", films);
        modelAndView.addObject("filmsCount",filmsCount);
        modelAndView.addObject("pagesCount",pagesCount);
        this.pages=pages;
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (filmService.checkTitle(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", pages);
            filmService.add(film);
        } else {
            modelAndView.addObject("message","part with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/add");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id,
                                 @ModelAttribute("message") String message) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("films") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (filmService.checkTitle(film.getTitle()) || filmService.getById(film.getId()).getTitle().equals(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", pages);
            filmService.edit(film);
        } else {
            modelAndView.addObject("message","part with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/edit/" +  + film.getId());
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        int filmsCount = filmService.filmsCount();
        int page = ((filmsCount - 1) % 10 == 0 && filmsCount > 10 && this.pages == (filmsCount + 9)/10) ?
                this.pages - 1 : this.pages;
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("page", page);
        Film film = filmService.getById(id);
        filmService.delete(film);
        return modelAndView;
    }
}


