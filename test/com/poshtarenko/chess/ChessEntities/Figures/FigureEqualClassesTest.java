package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureEqualClassesTest {

    @Test
    void createWithWhiteColor() {
        Coords coords = new Coords(5,4);
        byte color = 0;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithBlackColor() {
        Coords coords = new Coords(5,4);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithWrongColor() {
        Coords coords = new Coords(5,4);
        byte color = -5;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new King(color, coords);
        });

        String expectedError = "Wrong color";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWithWrongColor2() {
        Coords coords = new Coords(5,4);
        byte color = 5;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new King(color, coords);
        });

        String expectedError = "Wrong color";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWithNormalCoords() {
        Coords coords = new Coords(5,4);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWrongCoordinateX() {
        int x = -3;
        int y = 5;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coords(x,y);
        });

        String expectedError = "Wrong X-coordinate";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWrongCoordinateX2() {
        int x = 13;
        int y = 5;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coords(x,y);
        });

        String expectedError = "Wrong X-coordinate";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWrongCoordinateY() {
        int x = 5;
        int y = -3;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coords(x,y);
        });

        String expectedError = "Wrong Y-coordinate";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWrongCoordinateY2() {
        int x = 5;
        int y = 13;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coords(x,y);
        });

        String expectedError = "Wrong Y-coordinate";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }
}