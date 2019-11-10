package org.homecinema.entities;

import java.util.ArrayList;

public class ExtendedMovie extends Movie {
    private ArrayList<String> countries;
    private ArrayList<String> genres;

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ExtendedMovie(Integer movieId, String movieRussianName, String movieOriginalName, Integer seriesAmount, Integer movieYear, String description, ArrayList<String> countries, ArrayList<String> genres) {
        super(movieId, movieRussianName, movieOriginalName, seriesAmount, movieYear, description);
        this.countries = countries;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Номер фильма в базе: " + getMovieId() + "; " +
                "Название на русском: " + getMovieRussianName() + "; " +
                "Оригинальное название: " + getMovieOriginalName() + "; " +
                "Количество серий: " + getSeriesAmount() + "; " +
                "Жанр(ы): " + getGenres()  + "; " +
                "Год выхода на экраны: " + getMovieYear() + "; " +
                "Страны: " + getCountries()  + "; " +
                "Описание: " + this.getDescription() + ".";
    }
}

