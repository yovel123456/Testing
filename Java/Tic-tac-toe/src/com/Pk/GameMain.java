package com.Pk;

import java.util.Scanner;

public class Main {

    // game board and its status
    private static final int ROWS = 3, COLS = 3; // number of rows and columns

    private static GameState currentState; // the current status of the game {PLAYING, DRAW, CROSS_WON, NOUGHT_WON}
    private static Seed currentPlayer; // The current player {CROSS, NOUGHT}
    private static int currentRow, currentCol; // current row and column

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        initGame(); // initialize the game board and current status
        // play game once
        do{
            playerMove(currentPlayer);
            updateGame(currentPlayer, currentRow, currentCol);
            printBoard();
            // print message if game is over
            if (currentState == GameState.CROSS_WON) {
                System.out.println("'X' won!");
            } else if (currentState == GameState.NOUGHT_WON) {
                System.out.println("'O' won!");
            } else if (currentState == GameState.DRAW){
                System.out.println("Its a draw!");
            }
            //switch player
            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == GameState.PLAYING); // repeat if game is not over
    }

    // player with "theSeed" makes one move, with input validation and updates the global variables "currentRow" and "currentCol"
    private static void playerMove(Seed theSeed){
        boolean validInput = false;
        do {
            if (theSeed == Seed.CROSS) {
                System.out.print("Player 'X', enter your move (row[1-3] col[1-3]): ");
            } else {
                System.out.print("Player 'O', enter your move (row[1-3] col[1-3]): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == Seed.EMPTY){
                currentRow = row;
                currentCol = col;
                board[currentRow][currentCol] = theSeed;
                validInput = true;
            }else {
                System.out.println("This move at (" + (1 + row) + "," + (1 + col) + ") is not valid. Try again...");
            }
        } while (!validInput);
    }

    // update the "currentState" after placing "theSeed"
    private static void updateGame(Seed theSeed, int currentRow, int currentCol){
        if (hasWon(theSeed, currentRow, currentCol)) { // checking for a winning move
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (isDraw()) { // checking for a draw
            currentState = GameState.DRAW;
        }
    }
}