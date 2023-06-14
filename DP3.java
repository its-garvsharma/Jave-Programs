import java.util.Arrays;
import java.util.HashSet;



public class DP3 {
    public static void main(String[] args) {
        int coin[]={2,5,3,6};
        int sum=10;
        // System.out.println(coinChange(coin, sum));
        int len[]={1,2,3,4,5,6,7,8};
        int price[]={1,5,8,9,10,17,17,20};
        int totRod=8;
        // System.out.println(rodCutting(len, price, totRod));
        String str1="abcdgh";
        String str2="acdghr";
        // System.out.println(lcs(str1, str2, str1.length(), str2.length()));
        // int dp[][]=new int[str1.length()+1][str2.length()+1];
        // for(int i=0;i<dp.length;i++){
        //     for(int j=0;j<dp[0].length;j++){
        //         dp[i][j]=-1;
        //     }
        // }
        // System.out.println(lcstb(str1, str2));
        // int arr[]={50,3,10,7,40,80};
        // System.out.println(lictb(arr));
        String word1="abcdef";
        String word2="aceg";
        // System.out.println(stringconverion(word1, word2));
        String s="baaabab";
        String p="a*ab";
        // System.out.println(isMatch(s, p));
        int n=5;
        int dp[]=new int[n+1];
        for(int i=0;i<dp.length;i++){
            dp[i]=-1;
        }
        System.out.println(catalanRec(5));
        System.out.println(catalanMemo(5, dp));
        // System.out.println(mountainRanges(n));
        
    }
    public static int mountainRanges(int n){
        int dp[]=new int[n+1];
        dp[0]=1;dp[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                int inside=dp[j];
                int outside=dp[i-j-1];
                dp[i]+=inside*outside;
            }
        }
        return dp[n];
    }
    public static int countingBST(int n){
        int dp[]=new int[n+1];
        dp[0]=1;dp[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                int left=dp[j];
                int right=dp[i-j-1];
                dp[i]+=left*right;
            }
        }
        return dp[n];
    }
    public static int catalantab(int n){
        int dp[]=new int[n+1];
        dp[0]=1;dp[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }
    public static int catalanMemo(int n,int dp[]){
        if(n==0||n==1){
            return 1;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        int ans=0;
        for(int i=0;i<n;i++){
            ans+=catalanMemo(i,dp)*catalanMemo(n-i-1,dp);
            dp[n]=ans;
        }
        return dp[n];
    }
    public static int catalanRec(int n){
        if(n==0||n==1){
            return 1;
        }
        int ans=0;
        for(int i=0;i<n;i++){
            ans+=catalanRec(i)*catalanRec(n-i-1);
        }
        return ans;
    }
    public static boolean isMatch(String s,String p){
        int n=s.length();
        int m=p.length();
        boolean dp[][]=new boolean[n+1][m+1];
        // initalize
        dp[0][0]=true;
        for(int i=1;i<n+1;i++){
            dp[i][0]=false;
        }
        for(int j=1;j<m+1;j++){
            if(p.charAt(j-1)=='*'){
                dp[0][j]=dp[0][j-1];
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?'){
                    dp[i][j]=dp[i-1][j-1];
                }else if(p.charAt(j-1)=='*'){
                    dp[i][j]=(dp[i-1][j] || dp[i][j-1]);
                }else{
                    dp[i][j]=false;
                }
            }
        }
        return dp[n][m];
    }
    public static int editDistance(String word1,String word2){
        int n=word1.length(),m=word2.length();
        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0){
                    dp[i][j]=j;
                }if(j==0){
                    dp[i][j]=i;
                }
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    // int add=dp[i-1][j]+1;
                    // int del=dp[i][j-1]+1;
                    // int remove=dp[i-1][j-1]+1;
                    dp[i][j]=Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1])+1;
                }
            }
        }

        return dp[n][m];
    }
    public static int stringconverion(String word1,String word2){
        int n=word1.length(),m=word2.length();
        int del=n-lcstb(word1, word2);
        int add=m-lcstb(word1, word2);
        int ans=add+del;
        return ans;
    }
    public static int lcs(int arr1[],int arr2[]){
        int n=arr1.length;
        int m=arr2.length;
        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0||j==0){
                    dp[i][j]=0;
                }
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(arr1[i-1]==arr2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    int ans1=dp[i-1][j];
                    int ans2=dp[i][j-1];
                    dp[i][j]=Math.max(ans1, ans2);
                }
            }
        }

        return dp[n][m];
        
    }
    public static int lictb(int arr[]){
        HashSet<Integer>set=new HashSet<>();
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        int arr2[]=new int[set.size()];
        int i=0;
        for (int num : set) {
            arr2[i]=num;
            i++;
        }
        Arrays.sort(arr2); //sorted unique ele
        return  lcs(arr,arr2);
    }
    public static int lcsubstring(String str1,String str2){
        int n=str1.length(),m=str2.length();
        int dp[][]=new int[n+1][m+1];
        int ans=0;
        for(int i=0;i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0||j==0){
                    dp[i][j]=0;
                }
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                    ans=Math.max(ans, dp[i][j]);
                }else{
                    dp[i][j]=0;
                }
            }
        }
        
        return ans;
    }
    public static int lcsMemo(String str1,String str2,int n,int m,int dp[][]){
        if(n==0 ||m==0){
            return 0;
        }
        if(dp[n][m]!=-1){
            return dp[n][m];
        }
        if(str1.charAt(n-1)==str2.charAt(m-1)){
            dp[n][m]=lcsMemo(str1, str2, n-1, m-1,dp)+1;
            return dp[n][m];
        }else{
            int ans1=lcsMemo(str1, str2, n-1, m,dp);
            int ans2=lcsMemo(str1, str2, n, m-1,dp);
            dp[n][m]= Math.max(ans1, ans2);
            return dp[n][m];
        }
    }
    public static int lcstb(String str1,String str2){
        int n=str1.length(),m=str2.length();
        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0||j==0){
                    dp[i][j]=0;
                }
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    int ans1=dp[i-1][j];
                    int ans2=dp[i][j-1];
                    dp[i][j]=Math.max(ans1, ans2);
                }
            }
        }
        return dp[n][m];
    }

    public static int lcs(String str1,String str2,int n,int m){
        if(n==0 ||m==0){
            return 0;
        }
        if(str1.charAt(n-1)==str2.charAt(m-1)){
            return lcs(str1, str2, n-1, m-1)+1;
        }else{
            int ans1=lcs(str1, str2, n-1, m);
            int ans2=lcs(str1, str2, n, m-1);
            return Math.max(ans1, ans2);
        }

    }
    public static void PrintDp(int arr[][]) {
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int coinChange(int coin[],int sum){
        int n=coin.length;
        int dp[][]=new int[n+1][sum+1];
        for(int i=0;i<n+1;i++){
            dp[i][0]=1;
        }
        for(int j=1;j<sum+1;j++){
            dp[0][j]=0;
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<sum+1;j++){
                if(coin[i-1]<=j){
                    dp[i][j]=dp[i][j-coin[i-1]]+dp[i-1][j];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        PrintDp(dp);
        return dp[n][sum];
    }
    public static int rodCutting(int len[],int price[],int totRod ){
        int n=price.length;
        int dp[][]=new int[n+1][totRod+1];

        for(int i=0;i<n+1;i++){
            dp[i][0]=0;
        }
        for(int j=0;j<n+1;j++){
            dp[0][j]=0;
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<totRod+1;j++){
                if(len[i-1]<=j){
                    dp[i][j]=Math.max(price[i-1]+dp[i][j-len[i-1]],dp[i-1][j]);
                }else{
                    dp[i][j]=dp[i-1][j]; 
                }

            }
        }
        PrintDp(dp);
        return dp[n][totRod];
    }
}
 