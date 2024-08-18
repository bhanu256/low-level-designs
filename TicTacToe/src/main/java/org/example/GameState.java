package org.example;

public class GameState {
    int currentPlayer;
    int playerCount;
    Board board;

    public GameState (Board board) {
        this.board = board;
    }

    public void updatePlayerCount () { this.playerCount += 1; }

    public int getCurrentPlayer () {
        currentPlayer = (currentPlayer + 1) % playerCount;
        return currentPlayer;
    }

    public boolean playerWon (Player player, Position position) {
        for (int i=0; i<board.board.length; i++) {
            if (board.board[i][position.y] != player.getSymbol()) return false;
        }

        for (int i=0; i<board.board.length; i++) {
            if (board.board[position.x][i] != player.getSymbol()) return false;
        }

        return true;
    }

    public boolean isGameDraw () {
        for (int i=0; i<board.board.length; i++) {
            for (int j=0; j<board.board.length; j++) {
                if (board.board[i][j] == null) return false;
            }
        }

        return true;
    }
}
