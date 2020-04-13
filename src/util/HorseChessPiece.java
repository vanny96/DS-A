package util;

import structures.GraphSearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HorseChessPiece {
    private int[] position = new int[2];

    public HorseChessPiece(char positionX, int positionY) {
        position[0] = positionX - 65;
        position[1] = positionY - 1;
    }

    private HorseChessPiece(int positionX, int positionY) {
        position[0] = positionX;
        position[1] = positionY;
    }

    private LinkedList<HorseChessPiece> possiblePositions(){
        LinkedList<HorseChessPiece> possiblePositions = new LinkedList<>();

        if(position[0]-1 >= 0 && position[1]-2 >= 0)
            possiblePositions.add(new HorseChessPiece(position[0]-1, position[1]-2));

        if(position[0]-2 >= 0 && position[1]-1 >= 0)
            possiblePositions.add(new HorseChessPiece(position[0]-2, position[1]-1));

        if(position[0]-2 >= 0 && position[1]+1 < 8)
            possiblePositions.add(new HorseChessPiece(position[0]-2, position[1]+1));

        if(position[0]-1 >= 0 && position[1]+2 < 8)
            possiblePositions.add(new HorseChessPiece(position[0]-1, position[1]+2));

        if(position[0]+1 < 8 && position[1]+2 < 8)
            possiblePositions.add(new HorseChessPiece(position[0]+1, position[1]+2));

        if(position[0]+2 < 8 && position[1]+1 < 8)
            possiblePositions.add(new HorseChessPiece(position[0]+2, position[1]+1));

        if(position[0]+2 < 8 && position[1]-1 >= 0)
            possiblePositions.add(new HorseChessPiece(position[0]+2, position[1]-1));

        if(position[0]+1 < 8 && position[1]-2 >= 0)
            possiblePositions.add(new HorseChessPiece(position[0]+1, position[1]-2));

        return possiblePositions;
    }

    public List<HorseChessPiece> shortestPath(char positionX, int positionY){
        HorseChessPiece voidPiece = new HorseChessPiece('@', 0); // -1, -1
        HorseChessPiece finalPiece = new HorseChessPiece(positionX, positionY);

        GraphSearch<HorseChessPiece> searchHorse = new GraphSearch<>(
                HorseChessPiece::possiblePositions,
                piece -> piece.equals(finalPiece),
                voidPiece
        );

        return searchHorse.breathFirstSearch(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorseChessPiece that = (HorseChessPiece) o;
        return Arrays.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(position);
    }

    @Override
    public String toString() {
        char[] positionRapperesentation = new char[2];
        positionRapperesentation[0] =(char) (position[0] + 65);
        positionRapperesentation[1] =(char) (position[1] + 1 + 48);
        return Arrays.toString(positionRapperesentation);
    }
}
