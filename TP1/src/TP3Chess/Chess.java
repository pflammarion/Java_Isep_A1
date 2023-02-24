package TP3Chess;

import java.util.Scanner;

public class Chess {
    private Cell[][] board;
    private Player[] players;
    private Player currentPlayer;
    public void play(){
        while (true) {
            createPlayers();
            initialiseBoard();
            while (!isCheckMate()) {
                printBoard();
                String move;
                do {
                    move = askMove();
                }
                while (!isValidMove(move));
                updateBoard(move);
                switchPlayer();
            }
        }
    }

    private void createPlayers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Le joueur 1 doit mettre son nom");
        //faire de type personne
        Player playerOne = new Player(scanner.nextLine(), false);
        System.out.println("Le joueur 2 doit mettre son nom");
        Player playerTwo = new Player(scanner.nextLine(), true);
        //faire de type personne
    }
    private void initialiseBoard(){

    }

    private void printBoard(){

    }

    private String askMove(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisissez votre moooooove (Nb1 Nc3, Pd1 Pd3, Pc1 Pc4Nb1 Nc3, Pd1 Pd3, Pc1 Pc4)");
        return scanner.nextLine();
    }

    private boolean isCheckMate(){
        return false;
    }

    private boolean isValidMove(String move){
        return true;
    }

    private void updateBoard(String move){

    }
    private void switchPlayer(){
        //bah switch
    }


}
