package com.poshtarenko.chess.FX_Entities;

import com.poshtarenko.chess.ChessEntities.Coords;
import com.poshtarenko.chess.ChessEntities.Tile;
import javafx.scene.shape.Rectangle;

public class RectangleWithTile extends Rectangle {
    private Tile tile;

    public RectangleWithTile(Tile tile) {
        this.tile = tile;
    }

    public Coords getCoords(){
        return tile.getCoords();
    }

    public byte getColor() { return tile.getColor(); }
}
