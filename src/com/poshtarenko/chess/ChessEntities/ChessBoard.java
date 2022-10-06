package com.poshtarenko.chess.ChessEntities;

import com.poshtarenko.chess.ChessEntities.Figures.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private final ArrayList<Tile> tiles;
    private final ArrayList<Figure> figures;
    private final ArrayList<Figure> killedFigures;

    public ChessBoard() {
        this.tiles = new ArrayList<>();
        initializeTiles();
        this.figures = new ArrayList<>();
        initializeGameFigures();
        this.killedFigures = new ArrayList<>();
    }

    public boolean moveFigure(Coords figureCoords, Coords movementCoords) {
        // Если на координатах перемещения есть вражеская фигура, записываем ее индекс в списке фигур
        int toRemoveIndex = -1;
        for (int i = 0; i < figures.size(); i++) {
            if (figures.get(i).getCoords().equals(movementCoords)) {
                toRemoveIndex = i;
            }
        }
        // Если фигура по заданым координатам фигуры существует и ее перемещение на координаты перемещения возможно,
        // тогда удалить вражескую фигуру (если она есть) и вернуть true (успешное перемещение)
        for (int i = 0; i < figures.size(); i++) {
            if (figures.get(i).getCoords().equals(figureCoords) && figures.get(i).canMove(movementCoords, figures)) {
                figures.get(i).move(movementCoords, figures);
                if (toRemoveIndex != -1) {
                    killedFigures.add(figures.get(toRemoveIndex));
                    figures.remove(toRemoveIndex);
                }
                return true;
            }
        }

        // Перемещение провалено
        return false;
    }

    public boolean isFigureCanMoveWhenCheck(King king, Figure figureToMove, Coords movementCoords){
        return king.availableMovesOnCheck(getFigures()).containsEntry(figureToMove, movementCoords);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Figure> getFigures() {
        return figures;
    }

    public ArrayList<Figure> getKilledFigures() {
        return killedFigures;
    }

    // Заполнение массива клеток с цветами в шахматном порядке
    private void initializeTiles() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                byte color = 0;
                if (i % 2 == 0) {
                    if (j % 2 == 0) color = 0;
                    if (j % 2 == 1) color = 1;
                } else if (i % 2 == 1) {
                    if (j % 2 == 0) color = 1;
                    if (j % 2 == 1) color = 0;
                }
                try {
                    tiles.add(new Tile(new Coords(i, j), color));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Заполнение массива фигур в соответствии со стартовыми положениями фигур в обычной шахматной игре
    private void initializeGameFigures() {
        for (int i = 0; i < 8; i++) {
            figures.add(new Pawn((byte) 1, new Coords(i, 1)));
            figures.add(new Pawn((byte) 0, new Coords(i, 6)));
        }
        figures.add(new Tower((byte) 1, new Coords(0, 0)));
        figures.add(new Tower((byte) 1, new Coords(7, 0)));
        figures.add(new Tower((byte) 0, new Coords(0, 7)));
        figures.add(new Tower((byte) 0, new Coords(7, 7)));

        figures.add(new Officer((byte) 1, new Coords(2, 0)));
        figures.add(new Officer((byte) 1, new Coords(5, 0)));
        figures.add(new Officer((byte) 0, new Coords(2, 7)));
        figures.add(new Officer((byte) 0, new Coords(5, 7)));

        figures.add(new Horse((byte) 1, new Coords(1, 0)));
        figures.add(new Horse((byte) 1, new Coords(6, 0)));
        figures.add(new Horse((byte) 0, new Coords(1, 7)));
        figures.add(new Horse((byte) 0, new Coords(6, 7)));

        figures.add(new King((byte) 1, new Coords(4, 0)));
        figures.add(new King((byte) 0, new Coords(4, 7)));

        figures.add(new Queen((byte) 1, new Coords(3, 0)));
        figures.add(new Queen((byte) 0, new Coords(3, 7)));
    }
}
