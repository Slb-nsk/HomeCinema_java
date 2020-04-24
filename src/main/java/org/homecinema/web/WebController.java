package org.homecinema.web;

import org.homecinema.dao.MoviesDao;
import org.homecinema.entities.*;
import org.homecinema.services.iMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {
    private iMovieService service;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> countries = new ArrayList<>();

    public WebController(iMovieService service) {
        this.service = service;
        this.genres = service.getGenresList();
        this.countries = service.getcountriesList();
    }


    @GetMapping("/")
    public ArrayList<ShortMovie> start() {
        return service.getFullCinemaList();
    }

    @GetMapping("/{movieId}")
    public ExtendedMovie movieById(@PathVariable int movieId) {
        return service.getCinemaById(movieId, genres, countries);
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody ExtendedMovie m) {
        service.addMovie(m.getMovieRussianName(),
                m.getMovieOriginalName(),
                m.getMovieYear().toString(),
                m.getSeriesAmount().toString(),
                m.getDescription(),
                m.getCountries(),
                m.getGenres(),
                genres,
                countries,
                m.getImageUrl()
        );
        this.genres = service.getGenresList();
        this.countries = service.getcountriesList();
    }

    @PutMapping("/{movieId}")
    public void updateMovie(@PathVariable int movieId, @RequestBody ExtendedMovie m) {
        service.updateMovie(movieId,
                m.getMovieRussianName(),
                m.getMovieOriginalName(),
                m.getMovieYear().toString(),
                m.getSeriesAmount().toString(),
                m.getDescription(),
                m.getCountries(),
                m.getGenres(),
                genres,
                countries,
                m.getImageUrl()
        );
        this.genres = service.getGenresList();
        this.countries = service.getcountriesList();
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable int movieId) {
        service.deleteMovie(movieId);
        this.genres = service.getGenresList();
        this.countries = service.getcountriesList();
    }
}

