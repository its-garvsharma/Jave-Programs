import java.util.*;
public class DP4 {
    public static void main(String[] args) {
        // int arr[]={3,7,4,5};
        // int n=arr.length;
        // System.out.println(mcmRec(arr, 1, arr.length-1));
        // int dp[][]=new int[n][n];
        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         dp[i][j]=-1;
        //     }
        // }
        // System.out.println(mcmTab(arr));
        int arr[]={2,3,1,1,4};
        System.out.println(minJumps(arr));


    }
    public static int minJumps(int nums[]){
        int n=nums.length;
        int dp[]=new int[n];
        Arrays.fill(dp,-1);
        dp[n-1]=0;
        for(int i=n-2;i>=0;i--){
            int steps=nums[i];
            int ans=Integer.MAX_VALUE;
            for(int j=i+1;j<=i+steps && j<n;j++){
                if(dp[j]!=-1){
                    ans=Math.min(ans, dp[j]+1);
                }
            }
            if(ans!=Integer.MAX_VALUE){
                dp[i]=ans;
            }
        }
        return dp[0];
    }
    public static int minPartition(int arr[]){
        int n=arr.length;
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
        }
        int w=sum/2;
        
        int dp[][]=new int[n+1][w+1];
        for(int i=1;i<n+1;i++){
            for(int j=1;j<w+1;j++){
                if(arr[i-1]<=j){
                    int include=arr[i-1]+dp[i-1][j-arr[i-1]];
                    int exclude=dp[i-1][j];
                    dp[i][j]=Math.max(include, exclude);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        int sum2=sum-dp[n][w];
        int mindiffernce=Math.abs(sum2-dp[n][w]);
        return mindiffernce;
    }
    public static int mcmTab(int arr[]){
        int n=arr.length;
        int dp[][]=new int[n][n];
        // initialization
        for(int i=0;i<n;i++){
            dp[i][i]=0;
        }
        for(int len=2;len<=n-1;len++){
            for(int i=1;i<=n-len;i++){
                int j=i+len-1; //col
                dp[i][j]=Integer.MAX_VALUE;
                for(int k=i;k<=j-1;k++){
                    int cost1=dp[i][k];
                    int cost2=dp[k+1][j];
                    int cost3=arr[i-1]*arr[k]*arr[j];
                    dp[i][j]=Math.min(dp[i][j], cost1+cost2+cost3);
                }
            }
        }
        PrintDp(dp);
        return dp[1][n-1];
    }
    public static int mcmMEMO(int arr[],int i,int j,int dp[][]){
        if(i==j){
            return 0;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int ans=Integer.MAX_VALUE;
        for(int k=i;k<=j-1;k++) {
            int cost1=mcmMEMO(arr, i, k,dp);
            int cost2=mcmMEMO(arr, k+1, j,dp);
            int cost3=arr[i-1]*arr[k]*arr[j];
            int finalcost=cost1+cost2+cost3;
            ans=Math.min(ans, finalcost);  
            
        }
        dp[i][j]=ans;  
        PrintDp(dp);
        return dp[i][j];
    }
    public static int mcmRec(int arr[],int i,int j){
        if(i==j){
            return 0;
        }
        int ans=Integer.MAX_VALUE;
        for(int k=i;k<=j-1;k++) {
            int cost1=mcmRec(arr, i, k);
            int cost2=mcmRec(arr, k+1, j);
            int cost3=arr[i-1]*arr[k]*arr[j];
            int finalcost=cost1+cost2+cost3;
            ans=Math.min(ans, finalcost);    
        }

        return ans;
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

}
