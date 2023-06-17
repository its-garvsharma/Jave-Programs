public class Gridways {
    public static void main(String[] args) {
        System.out.println(RatMaze(0, 0, 3, 3));
    }
    public static int Gridway(int i,int j,int n,int m){
        // base case
        if(i==n-1 &&j==m-1){ 
            return 1;
        }else if(i==n || j==m){
            return 0;
        }
        
        int w1=Gridway(i+1, j, n, m);
        int w2=Gridway(i, j+1, n, m);
        return w1+w2;
    }
    public static int grid(int i,int j, int n,int m){
        if(i==n-1 &&j==m-1){
            return 1;
        }else if(i==n||j==m){
            return 0;
        }
        return grid(i+1, j, n, m)+grid(i, j+1, n, m);
    }
    public static int RatMaze(int i,int j,int n,int m){
        if(i==n-1 && j==m-1){
            return 1;
        }else if(i==n||j==m){
            return 0;
        }
        int w1=RatMaze(i+1, j, n, m);
        int w2=RatMaze(i, j+1, n, m);
        int w3=RatMaze(i-1, j, n, m);
        int w4=RatMaze(i, j-1, n, m);
        return w1+w2+w3+w4;
    }
}