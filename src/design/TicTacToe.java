package design;

public class TicTacToe {

    int[][] board ;
    int size;
    int[] rowSum;
    int[] colSum;
    int diagSum;
    int reverseDiagSum;
       /** Initialize your data structure here. */
        public TicTacToe(int n) {
            board = new int[n][n];
            size = n;
            rowSum=new int[n];
            colSum =new int[n];
            diagSum = 0;
            reverseDiagSum =0;
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            if(row<0 || col<0 || row>=size || col>=size ){
                throw new IllegalArgumentException("Invalid Move");
            }
            if(player!=1 && player!=2)  throw new IllegalArgumentException("Invalid Player");
            player =player==1? 1 : -1;
            board[row][col] = player;
            rowSum[row] +=player;
            colSum[col] +=player;
            if(row==col) diagSum += player;
            if(row==size-1-col) reverseDiagSum +=player;
            if(Math.abs(rowSum[row])==size || Math.abs(colSum[col])==size || Math.abs(diagSum)==size || Math.abs(reverseDiagSum)==size){
                return player==1?1:2;
            }
            return 0;
        }

}
