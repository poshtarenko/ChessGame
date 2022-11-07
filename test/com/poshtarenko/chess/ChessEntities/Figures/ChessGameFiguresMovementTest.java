package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.ChessGame;
import com.poshtarenko.chess.ChessEntities.Coords;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessGameFiguresMovementTest {

    ChessGame chessGame;

    @BeforeEach
    void init() {
        chessGame = new ChessGame();
    }

    @Test
    void moveBlackFigureWhenWhitesTurn() {
        chessGame = new ChessGame();

        Coords whiteFigureCoords = new Coords(1, 1);
        Coords movementCoords = new Coords(1, 2);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> chessGame.makeMove(whiteFigureCoords, movementCoords));

        String error = exception.getMessage();
        String expectedError = "Wrong figure color";

        assertTrue(error.contains(expectedError));
    }

    @Test
    void moveAgainstTheRules() {
        Coords figureCoords = new Coords(1, 7);
        Coords movementCoords = new Coords(5, 5);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> chessGame.makeMove(figureCoords, movementCoords));

        String error = exception.getMessage();
        String expectedError = "Move is impossible";

        assertTrue(error.contains(expectedError));
    }

    @Test
    void moveWhenKingWillBeCheck() {
        chessGame.makeMove(new Coords(4, 6), new Coords(4, 5));
        chessGame.makeMove(new Coords(0, 1), new Coords(0, 2));
        chessGame.makeMove(new Coords(3, 7), new Coords(7, 3));

        Coords figureCoords = new Coords(5, 1);
        Coords movementCoords = new Coords(5, 2);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> chessGame.makeMove(figureCoords, movementCoords));

        String error = exception.getMessage();
        String expectedError = "Move is impossible";

        assertTrue(error.contains(expectedError));
    }

    @Test
    void moveWhenAllIsOK() {
        chessGame.makeMove(new Coords(4, 6), new Coords(4, 5));
        chessGame.makeMove(new Coords(0, 1), new Coords(0, 2));
        chessGame.makeMove(new Coords(3, 7), new Coords(7, 3));
    }
}
