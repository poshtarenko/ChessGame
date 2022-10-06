package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;

import java.util.List;

public class Officer extends Figure {

    public Officer(byte color, Coords coords) {
        super(color, coords);
    }

    @Override
    public boolean moveIsPossible(Coords coords, List<Figure> otherFigures) {
        if (this.getCoords().equals(coords)) return false;

        // Проверяет есть ли дружественная фигура на клетке
        for (Figure figure : otherFigures)
            if (figure.getCoords().equals(coords) && figure.getColor() == this.getColor()) return false;

        // Основное правило ходьбы
        if (Math.abs(coords.getX() - this.getCoords().getX()) != Math.abs(coords.getY() - this.getCoords().getY()))
            return false;

        // Следующий массив кода ограничивает движение фигуры, чтобы она не могла "перепрыгивать" другие фигуры
        int vectorLength = Math.abs(this.getCoords().getX() - coords.getX());
        int directionX, directionY;

        if (this.getCoords().getX() < coords.getX()) directionX = 1;
        else if (this.getCoords().getX() > coords.getX()) directionX = -1;
        else return false;

        if (this.getCoords().getY() < coords.getY()) directionY = 1;
        else if (this.getCoords().getY() > coords.getY()) directionY = -1;
        else return false;

        for (int i = 2; i <= vectorLength; i++) {
            for (Figure figure : otherFigures)
                if (figure.getCoords().equals(new Coords(this.getCoords().getX() + (directionX * i) - directionX,
                        this.getCoords().getY() + (directionY * i) - directionY))) return false;
        }


        return true;
    }

    private boolean otherFigureOnTile(Coords coords, Iterable<Figure> otherFigures) {
        for (Figure figure : otherFigures) {
            if (figure.getCoords().equals(coords)) return true;
        }
        return false;
    }

    @Override
    public String getImageName() {
        return "officer.png";
    }
}
