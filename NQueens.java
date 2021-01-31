public class NQueens {
    
    private static class ChessBoard {

        boolean[][] grid;
        int curPrint;

        ChessBoard(int n) {
            grid = new boolean[n][n];
            curPrint = 0;
        }

        private void print() {
            curPrint++;
            String horzLine = " - ".repeat(grid.length+1);
            System.out.println("#" + curPrint);
            System.out.println(horzLine);
            for (int i = 0; i < grid.length; i++) {
                System.out.print("|  ");
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j])
                        System.out.print("Q  ");
                    else
                        System.out.print(".  ");
                }
                System.out.println("|");
            }
            System.out.println(horzLine);
        }

        private boolean get(int m, int n) {
            return grid[n][m];
        }

        private void set(int m, int n, boolean val) {
            grid[n][m] = val;
        }

        private int size() {
            return grid.length;
        }

        private void clear() {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    grid[i][j] = false;
                }
            }
        }
    }

    // is there a queen in the row or column, or diagonals?
    private static boolean placementValid(ChessBoard board, int row, int col) {
        // Check diagonals
        for (int curRow = 0; curRow < board.size(); curRow++) {
            for (int curCol = 0; curCol < board.size(); curCol++) {
                // Check row
                if (board.get(row, curCol))
                    return false;               // Something in the same row
                
                // Check column
                if (board.get(curRow, col))
                    return false;
    
                // Check diagonals
                double xDiff = curRow - row;                // If slope formula check passes, check if tile occupied
                double yDiff = curCol - col;                // Need floating point so we don't get false positives with int division
                if (xDiff != 0 && (((int)yDiff / xDiff == 1) || ((int)yDiff / xDiff == -1))) {   // Check not dividing by 0 aka vertical line
                    // On a diagonal
                    if (board.get(curRow, curCol))
                        return false;
                }
            }
        }
        return true;
    }

    // Print all valid solutions for 8-queen
    private static void boardSolver(ChessBoard board, int col) {
        if (col > board.size() - 1) {
            board.print();
            System.out.println();
            return;                                             // Got a winner, unwind
        }

        for (int row = 0; row < board.size(); row++) {
            if (placementValid(board, row, col) || col == 0) {
                board.set(row, col, true);
                boardSolver(board, col + 1);
                board.set(row, col, false);                     // Reset if the config invalid
            }
        }
        // No solutions
    }

    private static void findSolutions(ChessBoard board) {
        boardSolver(board, 0);
    }

    public static void main(String[] args) {
        ChessBoard board = new ChessBoard(8);
        findSolutions(board);
    }
}
