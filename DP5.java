import java.util.ArrayList;
import java.util.Arrays;

public class DP5 {
    public static void main(String[] args) {
        // int n=20;
        // int dp[]=new int[n+1];
        // Arrays.fill(dp, -1);
        // for(int i= 0;i<n;i++){
        //     System.out.print(tribonnicSeriesMemo(i, dp)+" ");
        // }System.out.println();
        // System.out.println(tribonnicSeriesMemo(4, dp));
        // int matrix[][]= { { 1, 2, 3, 4 },{ 2, 2, 3, 4 },{ 3, 2, 3, 4 },{ 4, 5, 6, 7 } };
        // System.out.println(longestPath1(matrix, 0, 0));
        // int nums[] = {2,7,9,3,1};
        // System.out.println(rob(nums));
        // ArrayList<String>list=new ArrayList<>();
        // int n=5;
        // String dp[][]=new String[n+1][n+1];
        int prices[]={3,3,5,0,0,3,1,4};
        System.out.println(uniquePaths(3, 7));
    }
    public static int robot(int i,int j,int m,int n){
        if(i==m-1||j==n-1){
            return 1;
        }else if(i==n||j==m){
            return 0;
        }
        int ans1=robot(i+1,j,m,n);
        int ans2=robot(i,j+1,m,n);
        return ans1+ans2;
        
    }
    public static int uniquePaths(int m, int n) {    
        return robot(0,0,m,n);
    }
    public static void paranthese(int n,String ans,int open,int close,ArrayList<String>list){
        if(ans.length()==2*n){
            list.add(ans);
            return;
        }
        if(open<n){
            paranthese(n, ans+"{", open+1, close, list);
        }if(close<open){
            paranthese(n, ans+"}", open, close+1, list);
        }
    } 
    public static int maxProfit(int[] prices) {
        int n=prices.length;
        int maxprofit=0;
        int count=0;
        int hold[]=new int[n];
        int free[]=new int[n];
        int ans[]=new int [n];
        hold[0]=-prices[0];
        free[0]=0;
        for(int i=1;i<n;i++){
            hold[i]=Math.max(hold[i-1],free[i-1]-prices[i]);
            free[i]=Math.max(free[i-1],hold[i-1]+prices[i-1]);
            ans[i]=free[i];
        }
        // Arrays.sort(ans);
        for(int i=n-1;i>n-3;i--){
            maxprofit+=ans[i];
        }
        for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]+" ");
        }System.out.println();
        return maxprofit;
    }
    public static void PrintDp(int arr[][]) {
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("Next line");
    }
    public static int tribonnicSeriesMemo(int n,int dp[]){
        if(n==0 ||n==1){
            return 0;
        }if(n==2){
            return 1;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        dp[n]=tribonnicSeriesMemo(n-1,dp)+tribonnicSeriesMemo(n-2,dp)+tribonnicSeriesMemo(n-3,dp);
        return dp[n];
    }
    public static int tribonnicSeries(int n){
        if(n==0 ||n==1){
            return 0;
        }if(n==2){
            return 1;
        }
        return tribonnicSeries(n-1)+tribonnicSeries(n-2)+tribonnicSeries(n-3);
    }
}
