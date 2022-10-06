package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;

import java.util.List;

public class Tower extends Figure {
    private boolean wasUsed = false;

    public Tower(byte color, Coords coords) {
        super(color, coords);
    }

    @Override
    public void move(Coords coords, List<Figure> otherFigures) {
        if (canMove(coords, otherFigures)) {
            setCoords(coords);
            wasUsed = true;
        }
    }

    @Override
    public boolean moveIsPossible(Coords coords, List<Figure> otherFigures) {
        if (coords.equals(this.getCoords())) return false;

        // Основное правило ходьбы
        if (this.getCoords().getX() != coords.getX() && this.getCoords().getY() != coords.getY()) return false;

        // Проверяет есть ли дружественная фигура на клетке
        for (Figure figure : otherFigures)
            if (figure.getCoords().equals(coords) && figure.getColor() == this.getColor()) return false;

        // Следующий массив кода ограничивает движение фигуры, чтобы она не могла "перепрыгивать" другие фигуры
        int directionX, directionY;

        if (this.getCoords().getX() < coords.getX()) directionX = 1;
        else if (this.getCoords().getX() > coords.getX()) directionX = -1;
        else directionX = 0;

        if (this.getCoords().getY() < coords.getY()) directionY = 1;
        else if (this.getCoords().getY() > coords.getY()) directionY = -1;
        else directionY = 0;

        int vectorLength = 0;

        if (directionX == 1 || directionX == -1) {
            vectorLength = Math.abs(this.getCoords().getX() - coords.getX());
            for (int i = 2; i <= vectorLength; i++) {
                for (Figure figure : otherFigures)
                    if (figure.getCoords().equals(new Coords(this.getCoords().getX() + (directionX * i) - directionX,
                            this.getCoords().getY()))) return false;
            }
        } else if (directionY == 1 || directionY == -1) {
            vectorLength = Math.abs(this.getCoords().getY() - coords.getY());
            for (int i = 2; i <= vectorLength; i++) {
                for (Figure figure : otherFigures)
                    if (figure.getCoords().equals(new Coords(this.getCoords().getX(),
                            this.getCoords().getY() + (directionY * i) - directionY))) return false;
            }
        }

        return true;
    }

    public boolean wasUsed() {
        return wasUsed;
    }

    @Override
    public String getImageName() {
        return "tower.png";
    }
}
