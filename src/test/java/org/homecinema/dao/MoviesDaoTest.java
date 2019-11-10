package org.homecinema.dao;

import java.util.List;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


//import org.homecinema.dao.MoviesDao;
import org.homecinema.entities.Movie;

import static org.mockito.Mockito.*;

public class MoviesDaoTest {

    @Test
    public void cinemaListTest(){
        MoviesDao dao = mock(MoviesDao.class);
        List<Movie> returnedValue = dao.cinemaList();
        assertNotNull(returnedValue);
    }

}


