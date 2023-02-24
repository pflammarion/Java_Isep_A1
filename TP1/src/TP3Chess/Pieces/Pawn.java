package TP3Chess.Pieces;

import TP3Chess.Cell;
import TP3Chess.Piece;
import TP3Chess.Position;

public class Pawn extends Piece {
    @Override
    public boolean isValidMove(Position newPosition, Cell[][] board){
        return false;
    }
    @Override
    public String toString(){
        return "P";
    }
}
