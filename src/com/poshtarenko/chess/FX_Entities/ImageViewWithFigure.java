package com.poshtarenko.chess.FX_Entities;

import com.poshtarenko.chess.ChessEntities.Figures.Figure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewWithFigure extends ImageView {
    Figure figure;

    public ImageViewWithFigure(Image image, Figure figure) {
        super(image);
        this.figure = figure;
    }

    public Figure getFigure() {
        return figure;
    }
}
