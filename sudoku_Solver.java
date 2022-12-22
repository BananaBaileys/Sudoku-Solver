
public class sudoku_Solver {
	
	/*
	 * a blank is a zero
	 */
	
	private static final int GRID_SIZE = 9;
	
	/*
	 *  3 helper methods to check if the number is already in the row, column, or square
	 *  true if number already exists, false if it doesn't
	*/
	
	// check for row
	private static boolean isNumInRow(int[][] board, int number, int row) {
		boolean result = false;
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == number) {
				result = true;
			}
		}
		return result;
	}
	
	// check for column
	private static boolean isNumInColumn(int[][] board, int number, int column) {
		boolean result = false;
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == number) {
				result = true;
			}
		}
		return result;
	}
		
	// check for box
	private static boolean isNumInBox(int[][] board, int number, int row, int column) {
		boolean result = false;
		// get the top left corner of every box [0-8]
		int localBoxRow = row - row % 3; 	// i.e. row 5 --> 5 - 2 = 3
		int localBoxColumn = column - column % 3;
		// check for the local grid
		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if (board[i][j] == number) {	// if number already in box
					result = true;
				}
			}
		}
		return result;
	}
	
	// method to combine all 3 methods above
	private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
		return !isNumInRow(board, number, row) &&
				!isNumInColumn(board, number, column) &&
				!isNumInBox(board, number, row, column);
	}
	
	/* traverse the board, row by row, if a cell doesn't have a number in it
	 * it will try to determine if the a number (0-9) is valid in the space
	 * If it goes though all the number in a given cell and none of them are valid
	 * then it backtracks.
	 * Backtrack:
	 * backtrack to the last number, try a different number 
	 * Repeat the same function until all cells are filled (recursion) 
	 */
	public static boolean solveBoard(int[][] board) {
		// loop entire the grid
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				if (board[row][column] == 0) { //when it sees a blank
					for (int numTry = 1; numTry <= GRID_SIZE; numTry++) { 	// loop through number 1-9
						if (isValidPlacement(board, numTry, row, column)) {
							board[row][column] = numTry;
							// recur here
							if (solveBoard(board)) {
								return true;		// the whole rest of the grid in the recursion are true
							} else {
								// if it can't be solve, it will go back and clear out numTry
								board[row][column] = 0;
							}
						}
					}
					//return false;	// none of the numbers are valid, the board is unsolvable 
				}
			}
		}
		return true; // if everything is filled out and the game is done
	}
	
	private static void printBoard(int[][] board) {
		for (int row = 0; row < GRID_SIZE; row++ ) { 
			if (row % 3 == 0 && row != 0) {
				System.out.println("-----------");
			}
			for (int column = 0; column < GRID_SIZE; column++ ) {
				if (column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				System.out.print(board[row][column]);
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		
		// some solvable board 
		// 0 is empty
		int[][] sudoku_puzzel_1 = {
				{0,0,0,6,9,0,5,0,7},
				{4,0,0,0,0,0,6,0,0},
				{0,0,7,0,2,1,0,0,3},
				{2,7,4,3,6,0,8,1,0},
				{5,0,0,0,0,0,2,9,0},
				{0,6,9,5,8,2,7,3,0},
				{3,4,0,0,5,6,0,0,0},
				{0,1,6,0,0,0,3,0,0},
				{0,0,0,1,0,0,0,0,2}
		};
		
		int[][] sudoku_puzzel_2 = {
				{0,0,0,6,7,0,8,9,2},
				{0,0,9,8,3,1,5,0,0},
				{0,2,0,5,4,0,0,6,0},
				{2,1,0,0,0,6,0,0,0},
				{4,0,6,0,0,7,3,8,0},
				{7,0,0,0,8,4,0,1,6},
				{3,7,4,0,0,0,0,5,0},
				{0,0,5,0,0,3,2,0,0},
				{0,0,0,0,1,0,7,3,8}
		};
		
		int[][] sudoku_puzzel_3 = {
				{7,0,9,4,0,0,0,6,8},
				{0,0,0,0,2,0,0,4,0},
				{0,0,3,0,0,0,0,9,0},
				{0,6,0,0,0,0,0,0,0},
				{0,0,0,0,0,1,5,0,0},
				{8,0,4,2,0,0,0,0,9},
				{0,3,0,7,0,0,0,0,0},
				{0,2,0,0,0,0,0,0,6},
				{6,0,7,0,5,0,9,0,0}
		};
	
		
		
		
		if (solveBoard(sudoku_puzzel_3)) {
			System.out.println("Solved successfully!");
		} else {
			System.out.println("Unsolveable board!");
		}
		
		printBoard(sudoku_puzzel_3);
		
	}

}
