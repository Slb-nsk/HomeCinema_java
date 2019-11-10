package org.homecinema.web;

import org.homecinema.dao.MoviesDao;
import org.homecinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {
    private final MoviesDao dao;
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> countries = new ArrayList<>();

    @Autowired
    public WebController(MoviesDao dao) {
        this.dao = dao;
        this.genres = dao.genresList();
        this.countries = dao.countriesList();
    }

    @GetMapping("/")
    public ArrayList<ShortMovie> start() {
        List<Movie> fullList = dao.cinemaList();
        ArrayList<ShortMovie> outputList = new ArrayList<>();
        for (Movie movie : fullList) {
            ShortMovie sm = new ShortMovie(movie.getMovieId(),
                                           movie.getMovieRussianName(),
                                           movie.getMovieOriginalName(),
                                           movie.getSeriesAmount(),
                                           movie.getMovieYear());
            outputList.add(sm);
        }
        return outputList;
    }

    @GetMapping("/{movieId}")
    public ExtendedMovie movieById(@PathVariable int movieId) {
        Movie qm = dao.cinemaById(movieId);

        List<Integer> listGenres = dao.movieGenre(movieId);
        ArrayList<String> movieGenres = new ArrayList<>();
        for (Integer i : listGenres) {
            movieGenres.add(genres.get(i));
        }

        List<Integer> listCountries = dao.movieCountry(movieId);
        ArrayList<String> movieCountries = new ArrayList<>();
        for (Integer i : listCountries) {
            movieCountries.add(countries.get(i));
        }

        ExtendedMovie em = new ExtendedMovie(movieId,
                                             qm.getMovieRussianName(),
                                             qm.getMovieOriginalName(),
                                             qm.getSeriesAmount(),
                                             qm.getMovieYear(),
                                             qm.getDescription(),
                                             movieCountries,
                                             movieGenres);
        return em;
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody ExtendedMovie m) {
        Movie addingCinema = new Movie(m.getMovieRussianName(),
                                       m.getMovieOriginalName(),
                                       m.getSeriesAmount(),
                                       m.getMovieYear(),
                                       m.getDescription());
        dao.addNewCinema(addingCinema);
        Integer cinemaId = dao.cinemaByRussianName(m.getMovieRussianName());
        if (m.getCountries() != null) {
            for (String c : m.getCountries()) {
                if (!countries.contains(c)) {
                    CountryEntity newCountry = new CountryEntity();
                    newCountry.setCountry(c);
                    dao.addNewCountry(newCountry);
                }
                dao.addMovieCountry(cinemaId, countries.indexOf(c));
            }
        }
        if (m.getGenres() != null) {
            for (String g : m.getGenres()) {
                if (!genres.contains(g)) {
                    GenreEntity newGenre = new GenreEntity();
                    newGenre.setGenre(g);
                    dao.addNewGenre(newGenre);
                }
                dao.addMovieGenre(cinemaId, genres.indexOf(g));
            }
        }
    }

    @PutMapping("/{movieId}")
    public void updateMovie(@PathVariable int movieId, @RequestBody ExtendedMovie m) {
        Movie updatingCinema = new Movie(movieId,
                                         m.getMovieRussianName(),
                                         m.getMovieOriginalName(),
                                         m.getSeriesAmount(),
                                         m.getMovieYear(),
                                         m.getDescription());
        dao.updateCinema(updatingCinema);

        List<Integer> listGenres = dao.movieGenre(movieId);
        for (String s : m.getGenres()) {
            if (genres.contains(s)) {
                int genreId = genres.indexOf(s);
                if (!listGenres.contains(genreId)) {
                    dao.addMovieGenre(movieId, genreId);
                } else {
                    listGenres.remove(listGenres.indexOf(genreId));
                }
            } else {
                GenreEntity newGenre = new GenreEntity();
                newGenre.setGenre(s);
                dao.addNewGenre(newGenre);
                int genreId = dao.genreByName(s);
                dao.addMovieGenre(movieId, genreId);
                genres = dao.genresList();
            }
        }
        for (Integer genreId : listGenres) {
            dao.deleteMovieGenre(movieId, genreId);
        }

        List<Integer> listCountries = dao.movieCountry(movieId);
        for (String s : m.getCountries()) {
            if (countries.contains(s)) {
                int countryId = countries.indexOf(s);
                if (!listCountries.contains(countryId)) {
                    dao.addMovieCountry(movieId, countryId);
                } else {
                    listCountries.remove(listCountries.indexOf(countryId));
                }
            } else {
                CountryEntity newCountry = new CountryEntity();
                newCountry.setCountry(s);
                dao.addNewCountry(newCountry);
                int countryId = dao.countryByName(s);
                dao.addMovieCountry(movieId, countryId);
                countries = dao.countriesList();
            }
        }
        for (Integer countryId : listCountries) {
            dao.deleteMovieCountry(movieId, countryId);
        }
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable int movieId) {
        System.out.println("movieId = " + movieId);
        List<Integer> listGenres = dao.movieGenre(movieId);
        for (Integer genreId : listGenres) {
            dao.deleteMovieGenre(movieId, genreId);
        }
        List<Integer> listCountries = dao.movieCountry(movieId);
        for (Integer countryId : listCountries) {
            dao.deleteMovieCountry(movieId, countryId);
        }
        dao.deleteMovie(movieId);
    }
}

