import java.util.ArrayList;

public class QueensPuzzleSolver  {

    public static class PT<E> implements PuzzleTest<E> {
        private int foundSolutions = 0;
        /*
        By nature of the storage of the Queens on a chessboard as an arrayList such that i is a row and
        arrayList.get(i) is the column the queen is on for that row, we are assured that there aren't two Queens
        on the same row or columm, therefore we must simply test the diagonals. Here we take the difference of the
        row and the column numbers for each queen and if the slope of the line that connects the two is 1 or -1
        i.e. the line is a 45 degree diagonal, then those two queens are on tiles that are diagonal to each other. 
        */
        public boolean test(ArrayList<E> candidate) {
            for (int thisRow = 0; thisRow < candidate.size(); thisRow++) {
                for (int otherRow = 0; otherRow < candidate.size(); otherRow++) {
                    double yDiff = (int)candidate.get(otherRow) - (int)candidate.get(thisRow);
                    double xDiff = otherRow - thisRow;
                    if (xDiff != 0 && yDiff != 0) {
                        double slope = yDiff / xDiff;
                        if (slope == 1.0 || slope == -1.0)      // FP error should be minimal, so == is okay
                            return false;
                    }
                }
            }
            return true;                                        // No mutual diagonals
        }
    
        /*
        Uses the solutions ArrayList, which stores the Queen's column position for each row to print the
        chessboard representation along with the number of the solution.
        */
        public void foundSolution(ArrayList<E> solution) {
            System.out.println("Solution #" + ++foundSolutions);                 // Increment and print
            for (int row = 0; row < solution.size(); row++) {
                for (int col = 0; col < solution.size(); col++) {
                    if ((int) solution.get(row) == col)                 // Explicit cast to int
                        System.out.print("Q  ");                        // If queen
                    else
                        System.out.print(".  ");                        // If empty tile
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        System.out.println("QueensPuzzleSolver - Azeb Ayalneh, Matthew Chesser, Jeffrey Knutsen, Cong Qin");
        int n = 8;                          // 8x8 chessboard
        // For i in ArrayList, i = row and ArrayList.get(i) = column that queen is in.
        ArrayList<Integer> solutions = new ArrayList<Integer>();
        ArrayList<Integer> universe = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            universe.add(i);                // Populate universe with all possible column numbers
        
        PuzzleTest<Integer> puzzleTest = new PT<Integer>();
        
        PuzzleSolve.solve(n, solutions, universe, puzzleTest);
    }
}
