package Graphs;
import java.util.*;
public class Graph {
    static class Edge{
        int src;
        int dest;
        int weight;
        public Edge(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.weight=w;
        }
    }
    static void createdGraph(ArrayList<Edge>[]graph){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2,15));   
        graph[0].add(new Edge(0, 3, 30));
        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));
        graph[2].add(new Edge(2, 0,15));            
        graph[2].add(new Edge(2, 3, 50));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));
    }
    static void createdGraph2(ArrayList<Edge>graph){
        graph.add(new Edge(0, 1, 2));
        graph.add(new Edge(0, 2,4));   
        graph.add(new Edge(1, 2, -4));
        graph.add(new Edge(2, 3, 2));
        graph.add(new Edge(3, 4, 4));            
        graph.add(new Edge(4, 1, -1));
    }
    public static void bfs(ArrayList<Edge>[]graph){
        Queue<Integer> q=new LinkedList<>();
        boolean vis[]=new boolean[graph.length];
        q.add(0);
        while(!q.isEmpty()){
            int curr=q.remove();
            if(!vis[curr]){
                System.out.print(curr+" ");
                vis[curr]=true;
                for(int i=0;i<graph[curr].size();i++){
                    Edge e=graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }
    public static void dfs(ArrayList<Edge>[]graph,int curr,boolean vis[]){
        // visit
        System.out.print(curr+" ");
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                dfs(graph, e.dest, vis);
            }
        }
    }
    public static boolean hasPath(ArrayList<Edge>[] graph,int src,int dest,boolean vis[]){
        if(src==dest){
            return true;
        }
        vis[src]=true;
        for(int i=0;i<graph[src].size();i++){
            Edge e=graph[src].get(i);
            //e.dest==neighbor
            if(!vis[e.dest] && hasPath(graph, e.dest, dest, vis)){
                return true;
            }
        }
        return false;
    }
    static class Pair implements Comparable<Pair>{
        int n;
        int path;
        public Pair(int n,int path){
            this.n=n;
            this.path=path;
        }
        @Override
        public int compareTo(Pair p2){
            return this.path-p2.path;
        }
    }
    public static void dijkstra(ArrayList<Edge>graph[],int src){
        int dist[]=new int[graph.length];
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Pair>pq=new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while(!pq.isEmpty()){
            Pair curr=pq.remove();
            if(!vis[curr.n]){
                vis[curr.n]=true;
                for(int i=0;i<graph[curr.n].size();i++){
                    Edge e=graph[curr.n].get(i);
                    int u=e.src;
                    int v=e.dest;
                    int wt=e.weight;
                    if(dist[u]+wt<dist[v]){
                        dist[v]=dist[u]+wt;
                        pq.add(new Pair(v, dist[v]));
                    }

                }
            }
        }
        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }System.out.println();
    }
    public static void bellmanFord(ArrayList<Edge>graph[],int src){
        int dist[]=new int[graph.length];
        for(int i=0;i<dist.length;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
        int V=graph.length;
        // algorithm
        for(int i=0;i<V-1;i++){
            // edge O(E)
            for(int j=0;j<graph.length;j++){
                for(int k=0;k<graph[j].size();k++){
                    Edge e=graph[j].get(k);
                    int u=e.src;
                    int v=e.dest;
                    int wt=e.weight;
                    // relaxtion
                    if(dist[u]!=Integer.MAX_VALUE && dist[u]+wt<dist[v]){
                        dist[v]=dist[u]+wt;
                    }
                }
            }
        }
        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }System.out.println();
    }
    public static void bellmanFord2(ArrayList<Edge>graph,int src,int V){
        int dist[]=new int[V];
        for(int i=0;i<dist.length;i++){
            if(i!=src){
                dist[i]=Integer.MAX_VALUE;
            }
        }
    
        // algorithm
        for(int i=0;i<V-1;i++){
            // edge O(E)
            for(int j=0;j<graph.size();j++){
                    Edge e=graph.get(j);
                    int u=e.src;
                    int v=e.dest;
                    int wt=e.weight;
                    // relaxtion
                    if(dist[u]!=Integer.MAX_VALUE && dist[u]+wt<dist[v]){
                        dist[v]=dist[u]+wt;
                    }
                
            }
        }
        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }System.out.println();
    }
    static class Pair2 implements Comparable<Pair2>{
        int v;
        int cost;
        public Pair2(int v,int c){
            this.v=v;
            this.cost=c;
        }
        @Override
        public int compareTo(Pair2 p2){
            return this.cost-p2.cost;
        }
    }
    public static void prims(ArrayList<Edge>[]graph){
        boolean[]vis=new boolean[graph.length];
        PriorityQueue<Pair2>pq=new PriorityQueue<>();
        pq.add(new Pair2(0, 0));
        int finalCost=0;
        while(!pq.isEmpty()){
            Pair2 curr=pq.remove();
            if(!vis[curr.v]){
                vis[curr.v]=true;
                finalCost+=curr.cost;

                for(int i=0; i<graph[curr.v].size();i++){
                    Edge e=graph[curr.v].get(i);
                    pq.add((new Pair2(e.dest, e.weight)));
                }
            }
        }
        System.out.println("Final cost : "+finalCost);
    }
    public static void prims2(ArrayList<Edge>[]graph){
        boolean[]vis=new boolean[graph.length];
        PriorityQueue<Pair2>pq=new PriorityQueue<>();
        ArrayList<Integer>arr=new ArrayList<>();
        pq.add(new Pair2(0, 0));
        int finalCost=0;
        while(!pq.isEmpty()){
            Pair2 curr=pq.remove();
            if(!vis[curr.v]){
                vis[curr.v]=true;
                arr.add(curr.cost);
                finalCost+=curr.cost;

                for(int i=0; i<graph[curr.v].size();i++){
                    Edge e=graph[curr.v].get(i);
                    pq.add((new Pair2(e.dest, e.weight)));
                }
            }
        }
        System.out.println("Final cost : "+finalCost);
        for(int i=0;i<arr.size();i++){
            System.out.print(arr.get(i)+" ");
        }System.out.println();
    }

    public static void main(String[] args) {
        int V=4;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = (ArrayList<Edge>[])new ArrayList[V];
        // ArrayList<Edge>graph=new ArrayList<>();
        createdGraph(graph);
        prims2(graph);



    }
}

