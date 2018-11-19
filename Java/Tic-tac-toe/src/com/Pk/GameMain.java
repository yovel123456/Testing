package com.Pk;

import java.util.Scanner;

public class GameMain {

    private Board board; // the game board
    private static GameState currentState; // the current status of the game {PLAYING, DRAW, CROSS_WON, NOUGHT_WON}
    private static Seed currentPlayer; // The current player {CROSS, NOUGHT}

    private static final Scanner in = new Scanner(System.in);

    private GameMain(){
        board = new Board(); // allocate game-board
        boolean keepPlaying = false;
        do {
            System.out.println("Do you wanna play or keep playing Tic Tac Toe: (true , false)");
            keepPlaying = in.nextBoolean();
            // initialize the game-board and current status
            initGame();
            // plays game once. players CROSS and NOUGHT move alternatively
            do {
                playerMove(currentPlayer); // update the content, currentRow currentCol
                board.paint(); // paint's the board
                updateGame(currentPlayer); // updates currentState
                // print message if game is over
                switch (currentState) {
                    case CROSS_WON:
                        System.out.println("'X' Won!");
                        break;
                    case NOUGHT_WON:
                        System.out.println("'O' Won!");
                        break;
                    case DRAW:
                        System.out.println("Its a draw");
                        break;
                }
                // switch player
                currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
            } while (currentState == GameState.PLAYING); // repeat until game is over
        } while (keepPlaying);
    }

    // initialize the game-board contents and the current states
    private void initGame() {
        board.init(); // clear the board contents
        currentPlayer = Seed.CROSS; // cross plays first
        currentState = GameState.PLAYING; // ready to play
    }

    // player with "theSeed" makes one move, with input validation and updates cell content, Board's currentRow and currentCol

    private void playerMove(Seed theSeed){
        boolean validInput = false;
        do {
            if (theSeed == Seed.CROSS)
                System.out.println("Player 'X', enter your move (row[1,3] col[1,3)");
            else
                System.out.println("Player 'O', enter your move (row[1,3] col[1,3)");
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if ((row >= 0 && row < Board.ROWS) && (col >=0 && col < Board.COLS) && board.cells[row][col].content == Seed.EMPTY) {
                board.cells[row][col].content = theSeed;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true; // input is ok exit loop;
            }
            else {
                System.out.println("this move at (" + (row+1) + ", " + (col+1) + ") is not valid. Try again");
            }
        } while(!validInput);
    }
    // update the "currentState" after "theSeed" moved

    private void updateGame(Seed theSeed){
        if (board.hasWon(theSeed)) { // check for a win
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if ( board.isDraw()) { // check for a draw
            currentState = GameState.DRAW;
        }
    }
    public static void main(String[] args) {
        new GameMain();
    }
}