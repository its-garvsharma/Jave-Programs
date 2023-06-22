package Heap;
import java.util.*;
public class PQ {
    static class Students implements Comparable<Students>{
        String name;
        int rank;
        public Students(String name,int rank){
            this.name=name;
            this.rank=rank;
        }
        @Override
        public int compareTo(Students s2){
            return this.rank -s2.rank;    
        }
    }
    static class Placement implements Comparable<Placement>{
        String name;
        int CGPA;
        public Placement(String name,int CGPA){
            this.name=name;
            this.CGPA=CGPA;
        }
        @Override
        public int compareTo(Placement c2){
            return this.CGPA -c2.CGPA;
        }
    }
    static class NearbyCars implements Comparable<NearbyCars>{
        String carName;
        int distance;
        
        public NearbyCars(String carName,int x,int y){
            this.carName=carName;
            this.distance=(int)Math.sqrt(x)+(int)Math.sqrt(y);
        }
        @Override
        public int compareTo(NearbyCars c2){
            return this.distance-c2.distance;
        }
    }
    static class Point implements Comparable<Point>{
        int x;
        int y;
        int distSq;
        int idx;

        public Point(int x,int y,int distSq,int idx){
            this.x=x;
            this.y=y;
            this.distSq=distSq;
            this.idx=idx;
        }
        @Override
        public int compareTo(Point p2){
            return this.distSq-p2.distSq;
        }
    }
    public static void ropes(){
        int ropes[]={2,3,3,4,6};
        PriorityQueue<Integer>pq=new PriorityQueue<>();
        for(int i=0;i<ropes.length;i++){
            pq.add(ropes[i]);
        }
        int cost=0;
        while(pq.size() >1){
            int min=pq.remove();
            int min2=pq.remove();
            cost+=min+min2;
            pq.add(min+min2);
        }
        System.out.println("cost of connecting n ropes = "+cost);
    }
    static class Row implements Comparable<Row>{
        int soldiers;
        int idx;

        public Row(int soldiers,int idx){
            this.soldiers=soldiers;
            this.idx=idx;
        }
        @Override
        public int compareTo(Row r2){
            if(this.soldiers==r2.soldiers){
                return this.idx-r2.idx;
            }else{
                return this.soldiers-r2.soldiers;
            }
        }
    }
    static class Pair implements Comparable<Pair>{
        int val;
        int idx;

        public Pair(int val,int idx){
            this.val=val;
            this.idx=idx;
        }

        @Override
        public int compareTo(Pair p2){
            // ascending
            // return this.val-p2.val;
            //DECENDING
            return p2.val-this.val;
        }
    }


    public static void main(String[] args) {
        int arr[]={1,3,-1,-3,5,3,6,7};
        int k=3; //window size
        int res[]=new int[arr.length-k+1];

        PriorityQueue<Pair>pq=new PriorityQueue<>();

        // 1st window
        for(int i=0;i<k;i++){
            pq.add(new Pair(arr[i], i));
        }
        res[0]=pq.peek().val;
        for(int i=k;i<arr.length;i++){
            while(pq.size()>0 && pq.peek().idx <=(i-k)){
                pq.remove();
            }
            pq.add(new Pair(arr[i], i));
            res[i-k+1]=pq.peek().val;
        }

        // print result
        for(int i=0;i<res.length;i++){
            System.out.print(res[i]+" ");
        }System.out.println( );
    }
}
