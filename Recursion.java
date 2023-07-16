import javax.naming.spi.StateFactory;
import javax.print.event.PrintServiceAttributeListener;
import javax.swing.text.html.StyleSheet;

public class Recursion {
    public static void main(String[] args) {
        // System.out.println(fibonaci(5));
        // printdecresing(10);
        int arr[]={4,5,6,7,0,1,2};
        int target=0;
        int tarIdx=search(arr, target,0,arr.length-1);
        System.out.println(tarIdx);
        // QuickSort(arr, 0, arr.length);
        // printarr(arr);
        // System.out.println(tillingProblem(4));
        // removeDuplicate("appnnacollege", 0,new StringBuilder(""), new boolean [26]);
        // System.out.println(FriendsParing(3));

    }
    public static int factorial(int n){
        if(n==0){
            return 1;
        }
        return n*factorial(n-1);
    }
    public static int power(int a,int n){
        if(n==0){
            return 1;
        }
        int halfPower=power(a,n/2);
        int squarepaower=halfPower*halfPower;
        if(n%2!=0){
            return a*squarepaower;
        }
        return squarepaower;
    }
    public static void printdecresing(int n){
        if(n==1){
            System.out.println(n);
            return;
        }printdecresing(n-1);
        System.out.println(n);
    }
    public static int fibonaci(int n){
        if(n==1 || n==0){
            return n;
        }
        return fibonaci(n-1)+fibonaci(n-2);   
    }
    public static boolean checkArrSorted(int arr[],int si){
        if(si==arr.length-1){
            return true;
        }
        if(arr[si]>arr[si+1]){
           return false; 
        }
        return checkArrSorted(arr, si+1);
    }
    public static int firstOccurance(int arr[],int key,int i){
        if(i==arr.length-1 ){
            if(arr[i]==key){
                return i;
            }else{
                return -1;
            }
        }
        if(arr[i]==key){
            return i;
        }
        return firstOccurance(arr, key, i+1);
    }
    public static int lastOccurance(int arr[],int key,int i){
        if(i==arr.length){
            return -1;
        }
        int isLast=lastOccurance(arr, key, i+1);
        if(isLast==-1 && arr[i]==key){
            return i;
        }
        return isLast;
    }
    public static int tillingProblem(int n){
        if(n==0||n==1){
            return 1;
        }
        return tillingProblem(n-1)+tillingProblem(n-2);
    }
    public static void removeDuplicate(String str,int idx,StringBuilder newStr,boolean map[]){
        if(idx==str.length()){
            System.out.println(newStr);
            return;
        }
        char curr=str.charAt(idx);
        if(map[curr-'a']==true){
            removeDuplicate(str, idx+1, newStr, map);
        }
        else{
            map[curr-'a']=true;
            removeDuplicate(str, idx+1, newStr.append(curr), map);
        }
    }
    public static int FriendsParing(int n){
        if(n==1||n==2){
            return n;
        }
        return FriendsParing(n-1)+(n-1)*FriendsParing(n-2);
    }
    // Merge Sort
    public static void printarr(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void MergeSort(int arr[],int si,int ei){
        if(si>=ei){
            return;
        }
        int mid =si+(ei-si)/2;
        MergeSort(arr, si, mid);
        MergeSort(arr, mid+1, ei);
        Merge(arr, si,mid, ei);
    }
    public static void Merge(int arr[],int si,int mid,int ei){
        int temp[]=new int[ei-si+1];
        int i=si;
        int j=mid+1;
        int k=0;
        while(i<=mid && j<=ei){
            if(arr[i]<arr[j]){
                temp[k]=arr[i];
                i++;
            }else{
                temp[k]=arr[j];
                j++;
            }k++;
        }
        while(i<=mid){
            temp[k++]=arr[i++];
        }
        while(j<=ei){
            temp[k++]=arr[j++];
            
        }
        for(k=0,i=si;k<temp.length;k++,i++){
            arr[i]=temp[k];
        }
    }
    public static void QuickSort(int arr[],int si,int ei){
        if(si>=ei){
            return;
        }
        int pIdx=partition(arr,si,ei);
        QuickSort(arr, si, pIdx-1);
        QuickSort(arr, pIdx, ei);
    }
    public static int partition(int arr[],int si,int ei){
        int pivot= arr[ei];
        int i=si-1;
        for(int j=si;j<ei;j++){
            if(arr[j]<=pivot){
                i++;
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
            }
        }
        i++;
        int temp=pivot;
        arr[ei]=arr[i];
        arr[i]=temp;
        return i;
    }
    public static int search(int arr[],int tar,int si,int ei){
        //base case
        if(si>ei){
            return -1;
        }
        // kaam
        int mid=si+(ei-si)/2;
        // case found
        if(arr[mid]==tar){
            return mid;
        }
        if(arr[si]<=arr[mid]){
            // case a: left
            if(arr[si]<=tar && tar<=arr[mid]){
                return search(arr, tar, si, mid-1);
            }else{
                // case b:right
                return search(arr, tar, mid+1, ei);
            }
        }else{
            if(arr[mid]<=tar && tar <=arr[ei]){
                return search(arr, tar, mid+1, ei);
            }else{
                return search(arr, tar, si, mid-1);
            }
        }
        
    }
}
