package Hashing;
import java.util.*;
public class Hashset {
    public static void countDisTelment(int arr[]){
        HashSet<Integer>set=new HashSet<>();
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        System.out.println(set.size());
    }
    public static void UnionAND_Inersection(int arr1[],int arr2[]){
        HashSet<Integer>union=new HashSet<>();
        TreeSet<Integer>set1=new TreeSet<>();
        for(int i=0;i<arr1.length ;i++){
            union.add(arr1[i]);
            set1.add(arr1[i]);
        }
        int count=0;
        for(int i=0;i<arr2.length ;i++){
            union.add(arr2[i]);
            if(set1.contains(arr2[i])){
                count++;
                System.out.print(arr2[i]+" ");
                set1.remove(arr2[i]);
            }
        }
        System.out.println("Intersection "+count);
        System.err.println("Union: "+union);
    }
    public static void SortByFrequency(String str){
        TreeMap<Character,Integer>map=new TreeMap<>();
        for(int i=0;i<str.length();i++){
            if(map.containsKey(str.charAt(i))){
                map.put(str.charAt(i),map.getOrDefault(str.charAt(i), 0)+1);
            }else{
                map.put(str.charAt(i), 1);
            }
        }
        String ans="";
        for (Character k : map.keySet()) {
            while(map.get(k)!=0){
                ans+=k;
                map.put(k, map.get(k)-1);
            }
        }
        System.out.println(ans);
    }
    public static void twoSum(int arr[],int tar){
        HashSet<Integer>set=new HashSet<>();
        int sum=0;
        for(int i=0;i<arr.length-1;i++ ){
            if(arr[i]+arr[i+1]==tar){
                set.add(i);
                set.add(i+1);
            }
        }
        System.out.println(set);
    }
    public static void twoSum2(int arr[],int tar){
        HashSet<Integer>set=new HashSet<>();
        int sum=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]+arr[j]==tar){
                    set.add(i);
                    set.add(j);
                }
            }
        }
        System.out.println(set);
    }
    public static void main(String[] args) {
        HashSet<String>cities=new HashSet<>();
        cities.add("Dehli");
        cities.add("Mumbai");
        cities.add("Noida");
        cities.add("Gwalior");
        cities.add("Aligarh");
        // System.out.println(cities);
        // Iterator it=cities.iterator();
        // while(it.hasNext()){
        //     System.err.println(it.next());
        // }
        // for(String city:cities){
        //     System.out.println(city);
        // }
        LinkedHashSet<String>lhs=new LinkedHashSet<>();
        lhs.add("Dehli");
        lhs.add("Mumbai");
        lhs.add("Noida");
        lhs.add("Gwalior");
        lhs.add("Aligarh");
        // System.out.println(lhs);

        TreeSet<String>ts=new TreeSet<>();
        ts.add("Dehli");
        ts.add("Mumbai");
        ts.add("Noida");
        ts.add("Gwalior");
        ts.add("Aligarh");
        // System.out.println(ts);
        // int arr[]={4,3,2,5,6,7,3,4,2,1};
        // countDisTelment(arr);
        int arr1[]={7,3,9};
        int arr2[]={6,3,9,2,9,4};
        // UnionAND_Inersection(arr1, arr2);
        // SortByFrequency("tree");
        int arr[]={1,2,4,5};
        twoSum2(arr, 1);
        String str="fds";
        
        int n=str.startsWith(str)

    }
}
