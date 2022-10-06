package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;

import java.util.List;

public class Horse extends Figure {

    public Horse(byte color, Coords coords) {
        super(color, coords);
    }

    @Override
    public boolean moveIsPossible(Coords coords, List<Figure> otherFigures) {
        if (this.getCoords() == coords) return false;

        //Проверяет есть ли дружественная фигура на клетке
        for (Figure figure : otherFigures)
            if (figure.getCoords().equals(coords) && figure.getColor() == this.getColor()) return false;

        if (Math.abs(this.getCoords().getX() - coords.getX()) == 2 && Math.abs(this.getCoords().getY() - coords.getY()) == 1)
            return true;
        else return Math.abs(this.getCoords().getY() - coords.getY()) == 2 && Math.abs(this.getCoords().getX() - coords.getX()) == 1;
    }

    @Override
    public String getImageName() {
        return "horse.png";
    }
}
