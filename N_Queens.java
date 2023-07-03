public class N_Queens {
    public static void main(String[] args) {
        int n=4;
        char board[][]=new char[n][n];
        // initailize
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j]='x';
            }
        }
        // nQueens(board,0);
        // if(nQueens_One_Solution(board, 0)){
        //     System.out.println("Solution is possible");
        //     printBoard(board);
        // }else{
        //     System.out.println("solution not possible");
        // }
        nQueens_count(board, 0);
        System.out.println("All possible Solutions are "+count);

    }
    static int count=0;

    public static void nQueensallcombination(char board[][],int row){
        //base
        if(row==board.length){
            printBoard(board);
            return;
        }
        //column loop
        for(int j=0;j<board.length;j++){
            board[row][j]='Q';
            nQueensallcombination(board, row+1); //fuction call
            board[row][j]='x';//backtracking step
        }
    }
    public static void nQueens(char board[][],int row){
        //base
        if(row==board.length){
            printBoard(board);
            return;
        }
        //column loop
        for(int j=0;j<board.length;j++){
            if(isSafe(board,row,j)){
                board[row][j]='Q';
                nQueens(board, row+1); //fuction call
                board[row][j]='x';//backtracking step
            } 
        }
    }
    public static void nQueens_count(char board[][],int row){
        //base
        if(row==board.length){
            // printBoard(board);
            count++;
            return;
        }
        //column loop
        for(int j=0;j<board.length;j++){
            if(isSafe(board,row,j)){
                board[row][j]='Q';
                nQueens_count(board, row+1); //fuction call
                board[row][j]='x';//backtracking step
            } 
        }
    }
    public static boolean nQueens_One_Solution(char board[][],int row){
        //base
        if(row==board.length){
            // printBoard(board);
            count++;
            return true;
        }
        //column loop
        for(int j=0;j<board.length;j++){
            if(isSafe(board,row,j)){
                board[row][j]='Q';
                if(nQueens_One_Solution(board, row+1)){
                    return true;
                } //fuction call
                board[row][j]='x';//backtracking step
            } 
        }
        return false;
    }
    public static boolean isSafe(char board[][],int row,int col){
        // vertical up
        for(int i=row-1;i>=0;i--){
            if(board[i][col]=='Q'){
                return false;
            }
        }
        // diagonal left up
        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--){
            if(board[i][j]=='Q'){
                return false;
            }
        }
        //diagonal right up
        for(int i=row-1,j=col+1;i>=0 &&j < board.length;i--,j++){
            if(board[i][j]=='Q'){
                return false;
            }
        }
        return true;
    }
    public static void printBoard(char board[][]){
        System.out.println("------chess board-----");
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }   
}