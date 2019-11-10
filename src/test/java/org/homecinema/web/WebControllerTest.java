package org.homecinema.web;

import org.homecinema.entities.ShortMovie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WebControllerTest {

    @BeforeAll
    static void initAll() {
    }

    @Test
    void startTest() {
        WebController wc = mock(WebController.class);
        ArrayList<ShortMovie> returnedValue = wc.start();
        assertNotNull(returnedValue);
    }
}


