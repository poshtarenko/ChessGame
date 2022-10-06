package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;

import java.util.List;

// Пешка
public class Pawn extends Figure {

    public Pawn(byte color, Coords coords) {
        super(color, coords);
    }

    @Override
    public boolean moveIsPossible(Coords coords, List<Figure> otherFigures) {
        int direction = 0;
        int coordYforDoubleMove = 0; //с какой линии пешка может совершить двойное перемещение
        if (getColor() == 0) {
            direction = -1;
            coordYforDoubleMove = 6;
        }
        if (getColor() == 1) {
            direction = 1;
            coordYforDoubleMove = 1;
        }

        if (this.getCoords() == coords) return false;

        // Основное правило ходьбы
        if (this.getCoords().getY() == coordYforDoubleMove) {
            if (coords.getY() != this.getCoords().getY() + 2 * direction && coords.getY() != this.getCoords().getY() + direction)
                return false;
        } else {
            if (coords.getY() != this.getCoords().getY() + direction) return false;
        }

        boolean enemyOnTile = false;
        boolean friendlyOnTile = false;

        for (Figure figure : otherFigures) {
            if (figure.getCoords().equals(coords) && figure.getColor() != this.getColor()) enemyOnTile = true;
            if (figure.getCoords().equals(coords) && figure.getColor() == this.getColor()) friendlyOnTile = true;
        }

        if (enemyOnTile) {
            if (coords.getX() != this.getCoords().getX() + 1 && coords.getX() != this.getCoords().getX() - 1)
                return false;
            if (coords.getY() == this.getCoords().getY() + 2 * direction) return false;
        } else {
            if (coords.getX() != this.getCoords().getX()) return false;
        }

        return !friendlyOnTile;
    }

    @Override
    public String getImageName() {
        return "pawn.png";
    }
}
