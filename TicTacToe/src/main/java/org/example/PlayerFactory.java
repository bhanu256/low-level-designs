package org.example;

public class PlayerFactory {

    static Player createPlayer(char c, PlacementStrategy strategy) {
        return new Player(new Symbol(c), strategy);
    }
}
