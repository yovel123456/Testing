public class Cell {
    seed content;
    int row, col;

    private static void printCell(Seed content) {
        switch (content) {
            case EMPTY:  System.out.print("   "); break;
            case CROSS:  System.out.print(" X "); break;
            case NOUGHT: System.out.print(" O "); break;
        }
    }
}
