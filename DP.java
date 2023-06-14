package DP;

import java.util.Arrays;

public class DP {
    public static void main(String[] args) {
        int n=5;
        int f[]=new int[n+1];
        Arrays.fill(f,-1);
        System.out.println(climbStair(n,f));
        System.out.println(climbTabular(n));
        
    }
    public static int fibo(int n,int dp[]){
        if(n<=0){
            return 0;
        }if(n==1){
            return 1;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        dp[n]=fibo(n-1,dp)+fibo(n-2,dp);
        return dp[n];
    }
    public static int fib(int n) {
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        return dp[n];
    }
    public static int fib(int n,int f[]){
        if(n==1 ||n==0){
            return n;
        }
        if(f[n]!=0){
            return f[n];
        }
        f[n]=fib(n-1, f)+fib(n-2, f);
        return f[n];
    }
    public static int fibTabulation(int n){
        int dp[]=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    public static int climbStair(int n,int f[]){
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        if(f[n]!=-1){
            return f[n];
        }
        f[n]= climbStair(n-1,f)+climbStair(n-2,f);
        return f[n];
    }
    public static int climbTabular(int n){
        int ways[]=new int[n+1];
        ways[0]=1;
        for(int i=1;i<=n;i++){
            if(i-2==-1){
                ways[i]=ways[i-1];
            }else{
                ways[i]=ways[i-1]+ways[i-2];
            }
        }
        return ways[n];
    }
}
