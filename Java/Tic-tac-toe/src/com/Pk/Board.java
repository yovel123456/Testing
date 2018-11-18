package com.Pk;

public class Board {

    // named-constants for the dimensions
    private static final int ROWS = 3;
    private static final int COLS = 3;

    // package access
    Cell[][] cells; // a board composes of ROWS-by-COLS Cell instances
    int currentRow, currentCol; // the current Seed's row and column

    // constructor to initialize the game board
    public Board(){
        cells = new Cell[ROWS][COLS]; // allocating the array
        for (int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++){
                cells[row][col] = new Cell(row, col); // allocate the elements of the array
            }
        }
    }

    // initialize (or re-initialize) the contents of the game board
    public void init() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].clear();
            }
        }
    }

    // return true if it is a draw (i.e., no more EMPTY cells)
    public boolean isDraw(){
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if(cells[row][col].content == Seed.EMPTY)
                    return false; // an empty cell found not a draw
            }
        }
        return true; // all cells are empty, its a draw.
    }

    // returns true if the player with 'theSeed" has won with last move
    private boolean hasWon(Seed theSeed) {
        return (cells[currentRow][0].content == theSeed         // checking 3 in a row
                && cells[currentRow][1].content == theSeed
                && cells[currentRow][2].content == theSeed
                || cells[0][currentCol].content == theSeed      // checking 3 in a column
                && cells[1][currentCol].content == theSeed
                && cells[2][currentCol].content == theSeed
                || currentRow == currentCol             // checking 3 in diagonal
                && cells[0][0].content == theSeed
                && cells[1][1].content == theSeed
                && cells[2][2].content == theSeed
                || currentRow + currentCol == 2         // checking 3 in the opposite diagonal
                && cells[0][2].content == theSeed
                && cells[1][1].content == theSeed
                && cells[2][0].content == theSeed);
    }

    // paint itself
    private void paint() {
        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                cells[row][col].paint(); // print each of the cells
                if(col < COLS - 1) {
                    System.out.print("|");  // print the vertical partition
                }
            }
            System.out.println();
            if(row < ROWS - 1) {
                System.out.println("-----------"); // print the horizontal partition
            }
        }
    }
}
