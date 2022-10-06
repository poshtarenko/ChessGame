package com.poshtarenko.chess.ChessEntities.Figures;

import com.poshtarenko.chess.ChessEntities.Coords;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class King extends Figure {
    private boolean wasUsed = false;

    public King(byte color, Coords coords) {
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
        if (this.getCoords().equals(coords)) return false;

        // Проверяет есть ли дружественная фигура на клетке
        for (Figure figure : otherFigures) {
            if (figure.getCoords().equals(coords) && figure.getColor() == this.getColor()) return false;
        }

        // Проверяет может ли какая-то вражеская фигура наступить на клетку
        List<Figure> otherFigures0 = new ArrayList<>(otherFigures);
        otherFigures0.removeIf(figure -> figure.getCoords().equals(coords) && figure.getColor() != this.getColor());

        for (Figure figure : otherFigures) {
            if (!(figure instanceof King) && figure.getColor() != this.getColor()) {
                King king = new King(getColor(), coords);
                otherFigures0.add(king);
                if (figure.moveIsPossible(coords, otherFigures0)) {
                    return false;
                }
                otherFigures0.remove(king);
            }
        }

        // Основное правило ходьбы
        return Math.sqrt(Math.pow(Math.abs(this.getCoords().getX() - coords.getX()), 2)
                + Math.pow(Math.abs(this.getCoords().getY() - coords.getY()), 2)) <= Math.sqrt(2);
    }

    public Multimap<Figure, Coords> availableMovesOnCheck(List<Figure> otherFigures) {
        Multimap<Figure, Coords> figureCoordsHashMap = ArrayListMultimap.create();

        ArrayList<Figure> otherFigures0 = new ArrayList<>(otherFigures);

        // Записываем в массив координаты, поставив на которые какую-либо фигуру, шах будет сброшен
        ArrayList<Coords> coordsList = new ArrayList<>();
        Coords coords;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                coords = new Coords(i, j);
                for (Figure figure : otherFigures0) {
                    if (figure.getCoords().equals(coords)) {
                        otherFigures0.remove(figure);
                        break;
                    }
                }
                otherFigures0.add(new Pawn(this.getColor(), coords));
                if (!check(otherFigures0)) {
                    coordsList.add(coords);
                }
                otherFigures0 = new ArrayList<>(otherFigures);
            }
        }

        System.out.println("CoordsList : " + coordsList);

        // Запись в конечный HashMap пар "фигура-координаты", которые могут спасти короля от шаха
        for (Coords coords1 : coordsList)
            for (Figure figure : otherFigures)
                if (figure.getColor() == this.getColor() && figure.moveIsPossible(coords1, otherFigures)) {
                    figureCoordsHashMap.put(figure, coords1);
                }

        // Запись в конечный HashMap пар "фигура(КОРОЛЬ)-координаты" для попыток "побега" короля от шаха
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (this.getCoords().getX() + i < 0 || this.getCoords().getY() + j < 0) continue;
                if (this.getCoords().getX() + i > 7 || this.getCoords().getY() + j > 7) continue;
                coords = new Coords(this.getCoords().getX() + i, this.getCoords().getY() + j);
                if (this.moveIsPossible(coords, otherFigures)) {
                    figureCoordsHashMap.put(this, coords);
                }
            }

        for (Map.Entry<Figure, Coords> entry : figureCoordsHashMap.entries())
            System.out.println(entry.getKey() + " " + entry.getValue());

        return figureCoordsHashMap;
    }

    public boolean checkmate(List<Figure> otherFigures) {

        if (!check(otherFigures)) return false;

        if (availableMovesOnCheck(otherFigures).size() > 0) return false;

        return true;
    }

    public boolean check(List<Figure> otherFigures) {
        for (Figure figure : otherFigures)
            if (!(figure instanceof King))
                if (figure.moveIsPossible(new Coords(this.getCoords().getX(), this.getCoords().getY()), otherFigures) && figure.getColor() != this.getColor()) {
                    return true;
                }
        return false;
    }

    @Override
    public String getImageName() {
        return "king.png";
    }
}
