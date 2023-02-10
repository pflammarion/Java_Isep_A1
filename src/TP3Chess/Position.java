package TP3Chess;

public class Position {
    String column;
    Integer row;
    public String toString() {
        return column.concat(row.toString());
    }
}
