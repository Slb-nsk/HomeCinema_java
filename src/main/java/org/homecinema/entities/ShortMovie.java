package org.homecinema.entities;

public class ShortMovie {
    private Integer movieId;
    private String movieRussianName;
    private String movieOriginalName;
    private Integer seriesAmount;
    private Integer movieYear;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieRussianName() {
        return movieRussianName;
    }

    public void setMovieRussianName(String movieRussianName) {
        this.movieRussianName = movieRussianName;
    }

    public String getMovieOriginalName() {
        return movieOriginalName;
    }

    public void setMovieOriginalName(String movieOriginalName) {
        this.movieOriginalName = movieOriginalName;
    }

    public Integer getSeriesAmount() {
        return seriesAmount;
    }

    public void setSeriesAmount(Integer seriesAmount) {
        this.seriesAmount = seriesAmount;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Integer movieYear) {
        this.movieYear = movieYear;
    }

    public ShortMovie(Integer movieId, String movieRussianName, String movieOriginalName, Integer seriesAmount, Integer movieYear) {
        this.movieId = movieId;
        this.movieRussianName = movieRussianName;
        this.movieOriginalName = movieOriginalName;
        this.seriesAmount = seriesAmount;
        this.movieYear = movieYear;
    }

    @Override
    public String toString() {
        return "Номер фильма в базе: " + movieId + "; " +
                "Название на русском: " + movieRussianName + "; " +
                "Оригинальное название: " + movieOriginalName + "; " +
                "Количество серий: " + seriesAmount + "; " +
                "Год выхода на экраны: " + movieYear + ".";
    }
}

