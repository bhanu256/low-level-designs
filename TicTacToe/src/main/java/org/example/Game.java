package org.example;

import java.util.*;

public class Game {

    List<Player> players;
    Board board;
    GameState state;

    public Game (int size) {
        players = new ArrayList<>();
        board = new Board(size);
        state = new GameState(board);
    }

    public void start () {
        while (true) {
            int currentPlayer = state.getCurrentPlayer();
            Player player = players.get(currentPlayer);

            Position position = player.place();

            board.update(position, player.getSymbol());

            if (state.playerWon(player, position)) {
                notify("Player: " + player.getSymbol() + "WOn");
                break;
            }

            if (state.isGameDraw()) {
                notify("Game Draw");
                break;
            }

            display();
        }
    }

    public void display() {
        for (int i=0; i<board.board.length; i++) {
            for (int j=0; j<board.board.length; j++) {
                System.out.print(board.board[i][j]);
            }
            System.out.println("");
        }
    }

    public void addPlayer (Player player) {
        this.players.add(player);
        this.state.updatePlayerCount();
    }

    public void notify (String str) {
        for (Player player: players) player.notify(str);
    }

}
