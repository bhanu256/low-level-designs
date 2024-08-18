package org.example;

public class NaiveStrategy implements PlacementStrategy {

    Board board;

    public NaiveStrategy (Board board) {this.board = board;}

    public Position place (Player player) {
        for (int i=0; i<board.board.length; i++) {
            for (int j=0; j<board.board.length; j++) {
                if (board.board[i][j] == null) {
                    return new Position(i, j);
                }
            }
        }

        return null;
    }
}
