package main;

import util.HorseChessPiece;

public class MainApp {
    public static void main(String[] args) {
        HorseChessPiece piece = new HorseChessPiece('A', 2);

        System.out.println(piece.shortestPath('F', 6));
    }
}
