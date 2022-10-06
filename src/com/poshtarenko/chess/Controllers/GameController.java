package com.poshtarenko.chess.Controllers;

import com.poshtarenko.chess.ChessEntities.ChessGame;
import com.poshtarenko.chess.ChessEntities.Coords;
import com.poshtarenko.chess.ChessEntities.Figures.*;
import com.poshtarenko.chess.ChessEntities.Tile;
import com.poshtarenko.chess.FX_Entities.ImageViewWithFigure;
import com.poshtarenko.chess.FX_Entities.RectangleWithTile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameController {

    private ChessGame chessGame = new ChessGame();
    private final int TILE_SIZE = 70;
    private Figure selectedFigure;
    private static final String IMAGES_PATH = "resources/images/figures/";
    private static final String WHITE_FIGURES_IMAGES_DIRECTORY = "white/";
    private static final String BLACK_FIGURES_IMAGES_DIRECTORY = "black/";

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private AnchorPane pane;
    @FXML
    private GridPane chessGrid;
    @FXML
    private GridPane figureImageGrid;
    @FXML
    private Label whoseTurnLabel;
    @FXML
    private Label gameOverLabel;
    @FXML
    private Label turnNumberLabel;
    @FXML
    private GridPane whiteLossesGrid;
    @FXML
    private GridPane blackLossesGrid;

    @FXML
    void restartGame(ActionEvent event) {
        chessGame = new ChessGame();
        updateAll();
    }

    @FXML
    void initialize() throws MalformedURLException {
        updateAll();
    }

    private void updateAll(){
        turnNumberLabel.setText(String.valueOf(chessGame.getTurn()));

        fillTilesTable();
        fillFigureTable();
        fillLossesTables();

        // Пишем в верхнее текстовое поле чей сейчас ход
        if(chessGame.getWhoseTurn() == 0) {
            whoseTurnLabel.setText("ХОД БЕЛЫХ");
            whoseTurnLabel.setStyle("-fx-text-fill: black; -fx-background-color: white; " +
                    "-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, white, 15, 0.7, 0.1, 2);");
        }
        if(chessGame.getWhoseTurn() == 1) {
            whoseTurnLabel.setText("ХОД ЧЁРНЫХ");
            whoseTurnLabel.setStyle("-fx-text-fill: white; -fx-background-color: black; " +
                    "-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, black, 15, 0.7, 0.1, 2);");
        }

        // Если поставлен шах и мат, заканчиваем игру и выводим соответствующие элемененты
        if(chessGame.isGameOver()){
            whoseTurnLabel.setVisible(false);

            chessGrid.setStyle(chessGrid.getStyle() + "-fx-opacity: 0.3;");
            figureImageGrid.setStyle(figureImageGrid.getStyle() + "-fx-opacity: 0.3;");

            gameOverLabel.setVisible(true);
            if(chessGame.getWinner() == 0){
                gameOverLabel.setText("ШАХ И МАТ!\nПОБЕДА БЕЛЫХ");
                gameOverLabel.setStyle("-fx-text-fill: black; -fx-background-color: white; " +
                        "-fx-background-radius: 40; -fx-effect: dropshadow(three-pass-box, white, 15, 0.7, 0.1, 2);");
            } else if (chessGame.getWinner() == 1){
                gameOverLabel.setText("ШАХ И МАТ!\nПОБЕДА ЧЕРНЫХ");
                gameOverLabel.setStyle("-fx-text-fill: white; -fx-background-color: black; " +
                        "-fx-background-radius: 40; -fx-effect: dropshadow(three-pass-box, black, 15, 0.7, 0.1, 2);");
            }
        }
    }

    private void fillTilesTable(){
        chessGrid.getChildren().clear();
        RectangleWithTile[] tiles = new RectangleWithTile[64];

        // Добавление массива клеток в табличное отображение
        for (int x = 0, r = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++, r++) {
                tiles[r] = newTileFX(chessGame.getTiles()[r]);
                chessGrid.add(tiles[r], x, y);
            }
        }
    }

    private RectangleWithTile newTileFX(Tile tile){
        RectangleWithTile tileFX = new RectangleWithTile(new Tile(tile.getCoords(), tile.getColor()));
        tileFX.setWidth(TILE_SIZE);
        tileFX.setHeight(TILE_SIZE);

        if(tileFX.getColor() == 0) tileFX.setStyle(tileFX.getStyle().concat("-fx-fill:" + Color.WHITE.getHex() + ";"));
        else if(tileFX.getColor() == 1) tileFX.setStyle(tileFX.getStyle().concat("-fx-fill:" + Color.BLACK.getHex() + ";"));

        tileFX.setOnMouseClicked(event -> {
            System.out.println(tileFX.getCoords());
            if(selectedFigure != null) {
                if (tileFX.getStyle().contains(Color.GREEN.getHex())) {
                    chessGame.makeMove(selectedFigure.getCoords(), tileFX.getCoords());
                    selectedFigure = null;
                    updateAll();
                }
            }
            System.out.println(chessGame.isGameOver());
        });

        return tileFX;
    }

    private void fillFigureTable(){
        figureImageGrid.getChildren().clear();
        String imageDirectory = IMAGES_PATH;
        String colorDirectory = "";
        String figureImageFilename = "";
        for(Figure figure : chessGame.getFigures()){
            if(figure.getColor() == 0) colorDirectory = WHITE_FIGURES_IMAGES_DIRECTORY;
            else if(figure.getColor() == 1) colorDirectory = BLACK_FIGURES_IMAGES_DIRECTORY;

            figureImageFilename = figure.getImageName();

            URL url = null;
            try {
                url = new File(imageDirectory.concat(colorDirectory).concat(figureImageFilename)).toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            ImageViewWithFigure imageView = getImageViewWithFigure(figure, url);
            figureImageGrid.add(imageView, figure.getCoords().getX(), figure.getCoords().getY());
        }
    }

    private ImageViewWithFigure getImageViewWithFigure(Figure figure, URL url) {
        Image image = new Image(String.valueOf(url));
        ImageViewWithFigure imageView = new ImageViewWithFigure(image, figure);
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        if(imageView.getFigure().getColor() == chessGame.getWhoseTurn()) imageView.getStyleClass().add("figure");

        imageView.setOnMouseClicked(event -> {
            if(selectedFigure != null) {
                chessGame.makeMove(selectedFigure.getCoords(), imageView.getFigure().getCoords());
                selectedFigure = null;
                updateAll();
                return;
            }

            fillTilesTable();
            if(chessGame.getWhoseTurn() != imageView.getFigure().getColor()) return;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    for (Node node : chessGrid.getChildren()) {
                        if(GridPane.getColumnIndex(node) == i && GridPane.getRowIndex(node) == j){
                            if(imageView.getFigure().moveIsPossible(new Coords(i,j), Arrays.asList(chessGame.getFigures()))) {
                                node.setStyle("-fx-fill:" + Color.GREEN.getHex() + ";");
                                node.setStyle(node.getStyle().concat("-fx-cursor: hand;"));
                                node.getStyleClass().add("tile");
                                selectedFigure = imageView.getFigure();
                            }
                        }
                    }
                }
            }
        });
        return imageView;
    }

    private void fillLossesTables(){
            whiteLossesGrid.getChildren().clear();
            blackLossesGrid.getChildren().clear();
            String path1 = "src/resources/images/figures/";
            String path2 = "";
            String path3 = "";

            int whiteGridX = 0, whiteGridY = 0, blackGridX = 0, blackGridY = 0;

            for(Figure figure : chessGame.getKilledFigures()){
                if(figure.getColor() == 0) path2 = WHITE_FIGURES_IMAGES_DIRECTORY;
                else if(figure.getColor() == 1) path2 = BLACK_FIGURES_IMAGES_DIRECTORY;

                if(figure instanceof Pawn) path3 = "pawn.png";
                else if(figure instanceof Tower) path3 = "tower.png";
                else if(figure instanceof Officer) path3 = "officer.png";
                else if(figure instanceof Horse) path3 = "horse.png";
                else if(figure instanceof King) path3 = "king.png";
                else if(figure instanceof Queen) path3 = "queen.png";

                URL url = null;
                try {
                    url = new File(path1.concat(path2).concat(path3)).toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Image image = new Image(String.valueOf(url));
                ImageViewWithFigure imageView = new ImageViewWithFigure(image, figure);
                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                
                if(figure.getColor() == 0) {
                    whiteLossesGrid.add(imageView, whiteGridX, whiteGridY);
                    whiteGridX++;
                    if(whiteGridX >= 3){
                        whiteGridX = 0;
                        whiteGridY++;
                    }
                }
                else if(figure.getColor() == 1) {
                    blackLossesGrid.add(imageView, blackGridX, blackGridY);
                    blackGridX++;
                    if(blackGridX >= 3){
                        blackGridX = 0;
                        blackGridY++;
                    }
                }
            }
    }
}

