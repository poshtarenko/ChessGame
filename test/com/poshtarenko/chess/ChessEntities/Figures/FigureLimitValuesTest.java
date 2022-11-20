package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FigureLimitValuesTest {

    @Test
    void createWithColorLimitValueMinus1() {
        Coords coords = new Coords(5,4);
        byte color = -1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new King(color, coords);
        });

        String expectedError = "Wrong color";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWithColorLimitValue0() {
        Coords coords = new Coords(5,4);
        byte color = 0;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithColorLimitValue1() {
        Coords coords = new Coords(5,4);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithColorLimitValueMinus2() {
        Coords coords = new Coords(5,4);
        byte color = 2;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new King(color, coords);
        });

        String expectedError = "Wrong color";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWithCoordinateXLimitValueMinus1() {
        int x = -1;
        int y = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coords(x,y);
        });

        String expectedError = "Wrong X-coordinate";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWithCoordinateXLimitValue0() {
        int x = 0;
        int y = 0;
        Coords coords = new Coords(x,y);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithCoordinateXLimitValue1() {
        int x = 1;
        int y = 0;
        Coords coords = new Coords(x,y);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithCoordinateXLimitValue6() {
        int x = 6;
        int y = 0;
        Coords coords = new Coords(x,y);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithCoordinateXLimitValue7() {
        int x = 7;
        int y = 0;
        Coords coords = new Coords(x,y);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithCoordinateXLimitValue8() {
        int x = 8;
        int y = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coords(x,y);
        });

        String expectedError = "Wrong X-coordinate";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWithCoordinateYLimitValueMinus1() {
        int x = 0;
        int y = -1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coords(x,y);
        });

        String expectedError = "Wrong Y-coordinate";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

    @Test
    void createWithCoordinateYLimitValue0() {
        int x = 0;
        int y = 0;
        Coords coords = new Coords(x,y);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithCoordinateYLimitValue1() {
        int x = 0;
        int y = 1;
        Coords coords = new Coords(x,y);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithCoordinateYLimitValue6() {
        int x = 0;
        int y = 6;
        Coords coords = new Coords(x,y);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithCoordinateYLimitValue7() {
        int x = 0;
        int y = 7;
        Coords coords = new Coords(x,y);
        byte color = 1;
        Figure figure = new King(color, coords);
    }

    @Test
    void createWithCoordinateYLimitValue8() {
        int x = 0;
        int y = 8;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Coords(x,y);
        });

        String expectedError = "Wrong Y-coordinate";
        String actualError = exception.getMessage();
        assertEquals(expectedError, actualError);
    }

}