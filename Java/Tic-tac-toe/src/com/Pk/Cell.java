package com.Pk;

class Cell {
    //package access
    Seed content; // content of the cell of type Seed
    private int row, col;

    Cell(int row, int col){
        this.row = row;
        this.col = col;
        clear();
    }

    // clear the seed content
    void clear() {
        this.content = Seed.EMPTY;
    }

    // paint itself
    void paint() {
        switch (content) {
            case EMPTY:  System.out.print("   "); break;
            case CROSS:  System.out.print(" X "); break;
            case NOUGHT: System.out.print(" O "); break;
        }
    }
}
