package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Game game = new Game(3);

        NaiveStrategy strategy = new NaiveStrategy(game.board);

        Player player1 = PlayerFactory.createPlayer('X', strategy);
        Player player2 = PlayerFactory.createPlayer('O', strategy);

        game.addPlayer(player1);
        game.addPlayer(player2);

        game.start();
    }
}