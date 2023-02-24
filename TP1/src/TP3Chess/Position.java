package TP3Chess;

public class Position {
    private String column;
    private Integer row;
    public String toString() {
        return column.concat(row.toString());
    }
}
