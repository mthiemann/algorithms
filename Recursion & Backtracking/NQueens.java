import java.util.*;
import Helpers.Timer;

class NQueens {

  public static boolean isAttacked(int row, int col, int[][] board) {

    // check row
    for (int i = 0; i < board[row].length; i++) {
      if (board[row][i] == 1) return true;
    }

    // check column
    for (int i = 0; i < board.length; i++) {
      if (board[i][col] == 1) return true;
    }

    int rows = board.length;
    int cols = board[0].length;

    int topDist = row;
    int botDist = rows - topDist - 1;
    int leftDist = col;
    int rightDist = cols - leftDist - 1;

    int leftTopSteps = leftDist <= topDist ? leftDist : topDist;
    int leftBotSteps = leftDist <= botDist ? leftDist : botDist;
    int rightTopSteps = rightDist <= topDist ? rightDist : topDist;
    int rightBotSteps = rightDist <= botDist ? rightDist : botDist;

    // check top left
    for (int i = 0; i < leftTopSteps; i++) {
      if (board[row-i-1][col-i-1] == 1) return true;
    }

    // check top right
    for (int i = 0; i < rightTopSteps; i++) {
      if (board[row-i-1][col+i+1] == 1) return true;
    }

    // check bottom left
    for (int i = 0; i < leftBotSteps; i++) {
      if (board[row+i+1][col-i-1] == 1) return true;
    }

    // check bottom right
    for (int i = 0; i < rightBotSteps; i++) {
      if (board[row+i+1][col+i+1] == 1) return true;
    }

    return false;
  }

  public static boolean NQueens(int[][] board, int nQueens) {
    // Base Case: all queens placed
    if (nQueens == 0) {
      return true;
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        // continue if field is attacked, otherwise place queen
        if (isAttacked(i, j, board)) {
          continue;
        } else {
          board[i][j] = 1;
        }

        // after queen was placed call function again with decremented nQueens
        // recursively call until nQueens == 0
        if (NQueens(board, nQueens - 1)) return true;

        // backtrack: undo last operation (reached when last call returned false)
        board[i][j] = 0;
      }
    }

    // no unattacked placement for queen found
    return false;
  }

  public static void main(String args[] ) throws Exception {

    //Scanner
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();

    int[][] board = new int[N][N];

    Timer timer = new Timer();
    timer.start();
    boolean hasSolution = NQueens(board, N);
    timer.stop();

    timer.printDuration();

    if (hasSolution) {
      System.out.println("YES");
    } else {
      System.out.println("NO");
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
}
