package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;

import java.util.List;

public class Queen extends Figure {
    public Queen(byte color, Coords coords) {
        super(color, coords);
    }

    @Override
    public boolean moveIsPossible(Coords coords, List<Figure> otherFigures) {
        // Королева ходит как оффицер или башня, поэтому если бы оффицер или башня на месте королевы могли бы сделать
        // предлагаемый ход, то и королева тоже сможет

        return new Officer(this.getColor(), this.getCoords()).moveIsPossible(coords, otherFigures)
                || new Tower(this.getColor(), this.getCoords()).moveIsPossible(coords, otherFigures);
    }

    @Override
    public String getImageName() {
        return "queen.png";
    }
}
