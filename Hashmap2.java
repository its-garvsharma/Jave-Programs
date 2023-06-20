package Hashing;
import java.util.*;
public class Hashmap2 {
    public static void largestSubarray0(int arr[]){
        HashMap<Integer,Integer> map=new HashMap<>();
        int sum=0;
        int len=0;
        for(int j=0;j<arr.length;j++){
            sum+=arr[j];
            if(map.containsKey(sum)){
                len=Math.max(len, j-map.get(sum));
            }else{
                map.put(sum, j);
            }
        }
        System.out.println("Largest sub array sum : "+len);
    }
    public static void subarrySumequalK(int arr[],int k){
        HashMap<Integer,Integer>map=new HashMap<>();
        int sum=0,ans=0;
        map.put(0, 1);
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(map.containsKey(sum-k)){
                ans+=map.get(sum-k);
            }
            map.put(sum,map.getOrDefault(sum, 0)+1);
        }
        System.out.println(ans);


    }
    public static void majoritElemet(int arr[]){
        HashMap<Integer,Integer>hm=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            int num=arr[i];
            if(hm.containsKey(num)){
                hm.put(num, hm.get(num)+1);
            }else{
                hm.put(num, 1);
            }
        }
        for (Integer k : hm.keySet()) {
            if(hm.get(k)>(arr.length/3)){
                System.out.println(k);
            }
        }
    }
    public static void majoritElemet2(int arr[]){
        HashMap<Integer,Integer>hm=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            hm.put(arr[i], hm.getOrDefault(arr[i],  0)+1);
        }
        for (Integer k : hm.keySet()) {
            if(hm.get(k)>(arr.length/3)){
                System.out.println(k);
            }
        }
    }
    public static boolean validAnagram(String str1,String str2){
        if(str1.length() !=str2.length()){
            return false;
        }
        HashMap<Character,Integer>hm=new HashMap<>();
        for(int i=0;i<str1.length();i++){
            hm.put(str1.charAt(i), hm.getOrDefault(str1.charAt(i), 0)+1);
        }
        for(int i=0;i<str2.length();i++){
            char ch=str2.charAt(i);
            if(hm.get(ch)!=null){
                if(hm.get(ch)==1){
                    hm.remove(ch);
                }else{
                    hm.put(ch, hm.get(ch)-1);
                }
            }
        }
        return hm.isEmpty();
    }
    public static String getStart(HashMap<String,String>tickets){
        HashMap<String,String>revMap=new HashMap<>();
        for(String key: tickets.keySet()){
            revMap.put(tickets.get(key), key);
        }
        for(String key: tickets.keySet()){
            if(!revMap.containsKey(key)){
                return key; //starting point
            }
        }
        return null;
    }
    public static void main(String[] args) {
        HashMap<String,String>tickets=new HashMap<>();
        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Dehli");
        tickets.put("Goa", "Chennai");
        tickets.put("Dehli", "Goa");
        String start =getStart(tickets);
        // System.out.print(start);
        for (String key : tickets.keySet()) {
            // System.out.print(" -> "+tickets.get(start));
            start=tickets.get(start);
        }
        // System.out.println();    
        int arr[]={10,2,-2,-20,10};
        // largestSubarray0(arr);
        subarrySumequalK(arr, -10);

    }
}
