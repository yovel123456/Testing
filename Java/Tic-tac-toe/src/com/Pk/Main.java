package com.Pk;

import java.util.Scanner;

public class Main {

    // enumerations for the seeds and cell contents
    public enum Seed {
        EMPTY, CROSS, NOUGHT
    }

    // enumerations for the various  states of the game
    public enum GameState {
        PLAYING, DRAW, CROSS_WON, NOUGHT_WON
    }

    // game board and its status
    private static final int ROWS = 3, COLS = 3; // number of rows and columns
    private static final Seed[][] board = new Seed[ROWS][COLS]; // game board containing {EMPTY, CROSS, NOUGHT}

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

    // initializing the game board status and current status
    private static void initGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = Seed.EMPTY; // all cells are empty
            }
        }
        currentState = GameState.PLAYING;
        currentPlayer = Seed.CROSS;
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

    // checking for a draw (no empty cells)
// TODO: declare draw if no player can "possibly" win
    private static boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == Seed.EMPTY) return false;
            }
        }
        return true;
    }

    private static boolean hasWon(Seed theSeed, int currentRow, int currentCol) {
        return (board[currentRow][0] == theSeed         // checking 3 in a row
                && board[currentRow][1] == theSeed
                && board[currentRow][2] == theSeed
                || board[0][currentCol] == theSeed      // checking 3 in a column
                && board[1][currentCol] == theSeed
                && board[2][currentCol] == theSeed
                || currentRow == currentCol             // checking 3 in diagonal
                && board[0][0] == theSeed
                && board[1][1] == theSeed
                && board[2][2] == theSeed
                || currentRow + currentCol == 2         // checking 3 in the opposite diagonal
                && board[0][2] == theSeed
                && board[1][1] == theSeed
                && board[2][0] == theSeed);
    }

    // print the game board
    private static void printBoard() {
        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                printCell(board[row][col]); // print each of the cells
                if(col != COLS - 1) {
                    System.out.print("|");  // print the vertical partition
                }
            }
            System.out.println();
            if(row != ROWS - 1) {
                System.out.println("-----------"); // print the horizontal partition
            }
        }
        System.out.println();
    }

    private static void printCell(Seed content) {
        switch (content) {
            case EMPTY:  System.out.print("   "); break;
            case CROSS:  System.out.print(" X "); break;
            case NOUGHT: System.out.print(" O "); break;
        }
    }
}