package TP3Chess;

public abstract class Piece {
    protected Position position;
    protected int color;
    public abstract boolean isValidMove(Position position, Cell[][] board);
    public abstract String toString();
}
