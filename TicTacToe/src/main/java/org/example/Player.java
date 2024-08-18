package org.example;

public class Player {

    private Symbol symbol;
    private PlacementStrategy strategy;

    public Player (Symbol symbol, PlacementStrategy strategy) {
        this.strategy = strategy;
        this.symbol = symbol;
    }

    public void notify (String str) {
        System.out.println(str);
    }

    public Position place () {
        return this.strategy.place(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
