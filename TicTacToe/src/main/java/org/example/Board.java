package org.example;

public class Board {
    Symbol[][] board;

    public Board (int size) {
        this.board = new Symbol[size][size];
    }

    public void update (Position position, Symbol symbol) {
        board[position.x][position.y] = symbol;
    }
}
