package Graphs;
import java.util.*;
public class Graph3 {
    static class Edge{
        int src,dest,weight;
        public Edge(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.weight=w;
        }
    }
    public static void createdGraph(int flights[][],ArrayList<Edge>graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<flights.length;i++){
            int src=flights[i][0];
            int dest=flights[i][1];
            int weight=flights[i][2];

            Edge e=new Edge(src, dest, weight);
            graph[src].add(e);
        }
    }
    static class Info{
        int v,cost,stops;
        public Info(int v,int c,int s){
            this.v=v;
            this.cost=c;
            this.stops=s;
        }
    }
    public static int chepestFlight(int n,int flights[][],int src,int dest,int k){
        @SuppressWarnings("unchecked")
        ArrayList<Edge>graph[]=new ArrayList[n];
        createdGraph(flights, graph);
        int dist[]=new int[n];
        for(int i=0;i<n;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        Queue<Info>q=new LinkedList<>();
        q.add(new Info(src, 0, 0));
        while(!q.isEmpty()){
            Info curr=q.remove();
            if(curr.stops>k){
                break;
            }
            for(int i=0;i<graph[curr.v].size();i++){
                Edge e=graph[curr.v].get(i);
                int u=e.src;
                int v=e.dest;
                int wt=e.weight;
                if( curr.cost+wt<dist[v] && curr.stops<=k){
                    dist[v]=curr.cost+wt;
                    q.add(new Info(v, dist[v], curr.stops+1));
                }
            }
        }
        if(dist[dest]==Integer.MAX_VALUE){
            return -1;
        }else{
            return dist[dest];
        }
    }
    static class Edge2 implements Comparable<Edge2>{
        int dest;
        int cost;
        public Edge2(int dest,int cost){
            this.cost=cost;
            this.dest=dest;
        }
        @Override
        public int compareTo(Edge2 p2){
            return this.cost-p2.cost;
        }
    }
    public static int connectCities(int cities[][]){
        PriorityQueue<Edge2>pq=new PriorityQueue<>();
        boolean vis[]=new boolean[cities.length];

        pq.add(new Edge2(0, 0));
        int finalCost=0;
        while(!pq.isEmpty()){
            Edge2 curr=pq.remove();
            if(!vis[curr.dest]){
                vis[curr.dest]=true;
                finalCost+=curr.cost;
                for(int i=0;i<cities[curr.dest].length;i++){
                    if(cities[curr.dest][i]!=0){
                        pq.add(new Edge2(i, cities[curr.dest][i]));
                    }
                }

            }
        }
        return finalCost;
    }
    public static void main(String[] args) {
        int cities[][]={{0,1,2,3,4},
                        {1,0,5,0,7},
                        {2,5,0,6,0},
                        {3,0,6,0,0},
                        {4,7,0,0,0}};
        
        System.out.println(connectCities(cities));

        

    }
}
