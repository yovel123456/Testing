package com.Pk;

import java.util.Scanner;

public class GameMain {

    private Board board; // the game board
    private static GameState currentState; // the current status of the game {PLAYING, DRAW, CROSS_WON, NOUGHT_WON}
    private static Seed currentPlayer; // The current player {CROSS, NOUGHT}

    private static final Scanner in = new Scanner(System.in);


    public GameMain(){
        board = new Board(); // allocate game-board

        // initialize the game-board and current status
        initGame();
        // plays game once. players CROSS and NOUGHT move alternatively
        do {
            playerMove(currentPlayer); // update the content

        }
    }

    // initialize the game-board contents and the current states
    private void initGame() {
        board.init(); // clear the board contents
        currentPlayer = Seed.CROSS; // cross plays first
        currentState = GameState.PLAYING; // ready to play
    }

    public static void main(String[] args) {

    }

    // player with "theSeed" makes one move, with input validation and updates the global variables "currentRow" and "currentCol"
    private static void playerMove(Seed theSeed){
        if (currentState == GameState.PLAYING) {

        }
    }

    // update the "currentState" after placing "theSeed"
    private static void updateGame(Seed theSeed, int currentRow, int currentCol){
        if (currentState == GameState.PLAYING)
            board[currentRow][currentCol] = theSeed;
        else if (currentState == GameState.DRAW)
    }
}