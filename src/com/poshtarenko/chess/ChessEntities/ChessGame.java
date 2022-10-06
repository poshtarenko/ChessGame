package com.poshtarenko.chess.ChessEntities;

import com.poshtarenko.chess.ChessEntities.Figures.Figure;
import com.poshtarenko.chess.ChessEntities.Figures.King;

import java.util.Arrays;

public class ChessGame {
    private int turn;
    private final ChessBoard chessBoard;
    private byte whoseTurn;
    private int winner;
    private boolean gameOver;

    public ChessGame() {
        turn = 0;
        chessBoard = new ChessBoard();
        whoseTurn = 0;
        winner = 3;
        gameOver = false;
    }

    // Выполнение хода с проверкой на цвет перемещаемой фигуры (он должен соответстовать переменной whoseTurn
    // и на успешность перемещения фигуры
    public void makeMove(Coords figureCoords, Coords movementCoords) {
        if (gameOver)
            throw new RuntimeException("You can not move figures when game is over");

        Figure figureToMove = null;

        for (Figure figure : chessBoard.getFigures())
            if (figure.getColor() == whoseTurn && figure.getCoords().equals(figureCoords))
                figureToMove = figure;

        if (figureToMove == null)
            throw new IllegalArgumentException("There are no any figure on provided coordinates");

        if (isCheck()) {
            King king = null;

            for (Figure figure : chessBoard.getFigures())
                if (figure instanceof King && figureToMove.getColor() == whoseTurn)
                    king = (King) figure;

            if (king == null)
                throw new IllegalArgumentException("There are can not exist a chess board without king");

            if (chessBoard.isFigureCanMoveWhenCheck(king, figureToMove, movementCoords))
                if (chessBoard.moveFigure(figureCoords, movementCoords)) nextTurn();
        } else {
            if (chessBoard.moveFigure(figureCoords, movementCoords)) nextTurn();
        }

        checkWinner();
    }

    private void nextTurn() {
        turn++;
        if (turn % 2 == 0) whoseTurn = 0;
        else if (turn % 2 == 1) whoseTurn = 1;
    }

    private void checkWinner() {
        for (Figure figure : getFigures()) {
            if (figure instanceof King && ((King) figure).checkmate(Arrays.asList(getFigures()))) {
                System.out.println("GAME OVER");
                if (figure.getColor() == 0) winner = 1;
                else if (figure.getColor() == 1) winner = 0;
                whoseTurn = -1;
                gameOver = true;
            }
        }
    }

    public Figure[] getFigures() {
        return chessBoard.getFigures().toArray(new Figure[0]);
    }

    public Figure[] getKilledFigures() {
        return chessBoard.getKilledFigures().toArray(new Figure[0]);
    }

    public Tile[] getTiles() {
        return chessBoard.getTiles().toArray(new Tile[0]);
    }

    public byte getWhoseTurn() {
        return whoseTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isCheck() {
        for (Figure figure : getFigures()) {
            if (figure instanceof King && figure.getColor() == whoseTurn) {
                return ((King) figure).check(Arrays.asList(getFigures()));
            }
        }
        return false;
    }

    public int getWinner() {
        return winner;
    }

    public int getTurn() {
        return turn;
    }
}
