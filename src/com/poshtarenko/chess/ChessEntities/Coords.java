package com.poshtarenko.chess.ChessEntities;

import java.util.Objects;

public class Coords {
    private final int x;
    private final int y;

    public Coords(int x, int y) {
        if (x > 7 || x < 0)
            throw new IllegalArgumentException("Wrong X-coordinate");
        if (y > 7 || y < 0)
            throw new IllegalArgumentException("Wrong Y-coordinate");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return x == coords.x && y == coords.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coords{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
