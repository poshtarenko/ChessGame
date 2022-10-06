package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

    @Test
    void movementOnOwnCoordinates() {
        King king = new King((byte) 1, new Coords(4, 4));
        Coords coordsToMove = new Coords(4, 4);
        List<Figure> figures = new ArrayList<>();

        assertFalse(king.moveIsPossible(coordsToMove, figures));
    }

    @Test
    void movementOnFriendlyFigure() {
        King king = new King((byte) 1, new Coords(4, 4));
        Coords coordsToMove = new Coords(5, 5);
        List<Figure> figures = new ArrayList<>();
        figures.add(new Pawn((byte) 1, coordsToMove));

        assertFalse(king.moveIsPossible(coordsToMove, figures));
    }

    @Test
    void movementOnFarCoordinates() {
        King king = new King((byte) 1, new Coords(0, 0));
        Coords coordsToMove = new Coords(5, 5);
        List<Figure> figures = new ArrayList<>();

        assertFalse(king.moveIsPossible(coordsToMove, figures));
    }

    @Test
    void movementOnCoordinatesWhereEnemyCanAttack() {
        King king = new King((byte) 1, new Coords(0, 0));
        Coords coordsToMove = new Coords(1, 0);
        List<Figure> figures = new ArrayList<>();
        figures.add(new Tower((byte) 0, new Coords(1, 7)));

        assertFalse(king.moveIsPossible(coordsToMove, figures));
    }

    @Test
    void movementMustBePossible() {
        King king = new King((byte) 1, new Coords(1, 1));
        Coords coordsToMove = new Coords(1, 0);
        List<Figure> figures = new ArrayList<>();
        figures.add(new Tower((byte) 0, new Coords(3, 7)));

        assertTrue(king.moveIsPossible(coordsToMove, figures));
    }

    @Test
    void checkAndCheckmateMustBeFalse() {
        King king = new King((byte) 1, new Coords(4, 0));

        List<Figure> otherFigures = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            otherFigures.add(new Pawn((byte) 1, new Coords(i, 1)));
            otherFigures.add(new Pawn((byte) 0, new Coords(i, 6)));
        }
        otherFigures.add(new Tower((byte) 1, new Coords(0, 0)));
        otherFigures.add(new Tower((byte) 1, new Coords(7, 0)));
        otherFigures.add(new Tower((byte) 0, new Coords(0, 7)));
        otherFigures.add(new Tower((byte) 0, new Coords(7, 7)));

        otherFigures.add(new Officer((byte) 1, new Coords(2, 0)));
        otherFigures.add(new Officer((byte) 1, new Coords(5, 0)));
        otherFigures.add(new Officer((byte) 0, new Coords(2, 7)));
        otherFigures.add(new Officer((byte) 0, new Coords(5, 7)));

        otherFigures.add(new Horse((byte) 1, new Coords(1, 0)));
        otherFigures.add(new Horse((byte) 1, new Coords(6, 0)));
        otherFigures.add(new Horse((byte) 0, new Coords(1, 7)));
        otherFigures.add(new Horse((byte) 0, new Coords(6, 7)));

        otherFigures.add(king);
        otherFigures.add(new King((byte) 0, new Coords(4, 7)));

        otherFigures.add(new Queen((byte) 1, new Coords(3, 0)));
        otherFigures.add(new Queen((byte) 0, new Coords(3, 7)));

        assertFalse(king.check(otherFigures));
        assertFalse(king.checkmate(otherFigures));
    }

    @Test
    void checkMustBeTrueButCheckmateMustBeFalse() {
        King king = new King((byte) 1, new Coords(0, 0));

        List<Figure> otherFigures = new ArrayList<>();
        otherFigures.add(king);
        otherFigures.add(new Tower((byte) 0, new Coords(0, 5)));

        assertTrue(king.check(otherFigures));
        assertFalse(king.checkmate(otherFigures));
    }

    @Test
    void checkAndCheckmateMustBeTrue() {
        King king = new King((byte) 1, new Coords(4, 0));

        List<Figure> otherFigures = new ArrayList<>();
        otherFigures.add(king);
        otherFigures.add(new Queen((byte) 0, new Coords(4, 1)));
        otherFigures.add(new Pawn((byte) 0, new Coords(3, 2)));

        assertTrue(king.check(otherFigures));
        assertTrue(king.checkmate(otherFigures));
    }
}