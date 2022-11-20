package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;

import java.util.ArrayList;
import java.util.List;

public abstract class Figure {
    private final byte color; // 0 = white, 1 = black
    private Coords coords;

    public Figure(byte color, Coords coords) {
        if (color != 0 && color != 1)
            throw new IllegalArgumentException("Wrong color");
        this.color = color;
        this.coords = coords;
    }

    public Figure(Figure figure) {
        this.color = figure.getColor();
        this.coords = figure.getCoords();
    }

    public boolean canMove(Coords coords, List<Figure> otherFigures) {
        return !moveWillCauseCheck(otherFigures) && moveIsPossible(coords, otherFigures);
    }

    public void move(Coords coords, List<Figure> otherFigures) {
        if (canMove(coords, otherFigures)) {
            setCoords(coords);
        }
    }

    public boolean moveWillCauseCheck(List<Figure> otherFigures) {
        // Если король уже под шахом - вернуть false
        for (Figure figure : otherFigures)
            if (figure instanceof King && figure.getColor() == this.color)
                if (((King) figure).check(otherFigures))
                    return false;

        otherFigures = new ArrayList<>(otherFigures);

        otherFigures.remove(this);

        for (Figure figure : otherFigures)
            if (figure instanceof King && figure.getColor() == this.color)
                if (((King) figure).check(otherFigures))
                    return true;

        return false;
    }

    public abstract boolean moveIsPossible(Coords coords, List<Figure> otherFigures);

    public byte getColor() {
        return color;
    }

    public Coords getCoords() {
        return coords;
    }

    protected void setCoords(Coords coords) {
        this.coords = coords;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "color=" + color +
                ", coords=" + coords +
                '}';
    }

    public String getImageName(){
        throw new RuntimeException("Override this method!");
    }


}
