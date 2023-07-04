package NQueen;

public class SudokuPrc {
    public static void main(String[] args) {
        int sudoku[][]={{3,0,6,5,0,8,4,0,0},
        {5,2,0,0,0,0,0,0,0},
        {0,8,7,0,0,0,0,3,1},
        {0,0,3,0,1,0,0,8,0},
        {9,0,0,8,6,3,0,0,5},
        {0,5,0,0,9,0,6,0,0},
        {1,3,0,0,0,0,2,5,0},
        {0,0,0,0,0,0,0,7,4},
        {0,0,5,2,0,6,3,0,0}};
        if(sudokuprogram(sudoku, 0, 0)){
            System.out.println("Solution exists");
            printSudoku(sudoku);
        }else{
            System.out.println("Solution does not exist");
        }
    }
    public static void printSudoku(int suduko[][]){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(suduko[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static boolean sodukoSolver(int sudoku[][],int row,int col){
        // base case
        if(row==9){
            return true;
        }
        int nextRow=row,nextCol=col+1;
        if(col+1==9){
            nextRow+=1;
            nextCol=0;
        }
        if(sudoku[row][col]!=0){
            return sodukoSolver(sudoku, nextRow, nextCol);
        }
        for(int digit=1;digit<=9;digit++){
            if(isSafe(sudoku,row,col,digit)){
                sudoku[row][col]=digit;
                if(sodukoSolver(sudoku, nextRow, nextCol)){
                    return true;
                }
                sudoku[row][col]=0;
            }
        }
        return false;
    }
    public static boolean isSafe(int sudoku[][],int row,int col,int digit){
        // col
        for(int i=0;i<=8;i++){
            if(sudoku[i][col]==digit){
                return false;
            }
        }
        for(int j=0;j<=8;j++){
            if(sudoku[row][j]==digit){
                return false;
            }
        }
        int sr=(row/3)*3;
        int sc=(col/3)*3;
        for(int i=sr;i<sr+3;i++){
            for(int j=sc;j<sc+3;j++){
                if(sudoku[i][j]==digit){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean sudokuprogram(int sudoku[][],int row,int col){
        if(row==9){
            return true;
        }
        int nextRow=row ;int nextCol=col+1;
        if(col+1==9){
            nextRow+=1;
            nextCol=0;
        }
        if(sudoku[row][col]!=0){
            return sudokuprogram(sudoku, nextRow, nextCol);
        }
        for(int digit=1;digit<=9;digit++){
            if(isTure(sudoku,row,col,digit)){
                sudoku[row][col]=digit;
                if(sudokuprogram(sudoku, nextRow, nextCol)){
                    return true;
                }
                sudoku[row][col]=0;
            }
        }
        return false; 
    }
    public static  boolean isTure(int sudoku[][],int row,int col,int digit){
        // col
        for(int i=0;i<=8;i++){
            if(sudoku[i][col]==digit){
                return false;
            }
        }
        // row
        for(int i=0;i<=8;i++){
            if(sudoku[row][i]==digit){
                return false;
            }
        }
        // for grid
        int sr=(row/3)*3;
        int sc=(col/3)*3;
        for(int i=sr;i<sr+3;i++){
            for(int j=sc;j<sc+3;j++){
                if(sudoku[i][j]==digit){
                    return false;
                }
            }
        }
        return true;
    }
}