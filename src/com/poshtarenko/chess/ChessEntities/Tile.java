package com.poshtarenko.chess.ChessEntities;

public class Tile {
    private final Coords coords;
    private final byte color; // 0 = white, 1 = black

    public Tile(Coords coords, byte color) {
        this.coords = coords;
        this.color = color;
    }

    public Coords getCoords() {
        return coords;
    }

    public byte getColor() {
        return color;
    }
}
