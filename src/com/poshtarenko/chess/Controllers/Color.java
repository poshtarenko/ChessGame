package com.poshtarenko.chess.Controllers;

enum Color {
    GREEN("#84e36f"), WHITE("#FFCB99"), BLACK("#8E6052");

    private final String hex;

    Color(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }
}
