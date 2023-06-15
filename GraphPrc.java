package Graphs;
import java.util.*;

import javax.swing.plaf.TextUI;



public class GraphPrc {
    static class Edge{
        int src,dest;
        public Edge(int s,int d){
            this.src=s;
            this.dest=d;
        }
    }
    public static void createdGraph(ArrayList<Edge>[]graph){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[1].add(new Edge(1, 8));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 9));
        graph[9].add(new Edge(9, 2));
        graph[8].add(new Edge(8, 6));
        graph[8].add(new Edge(8, 5));
        graph[8].add(new Edge(8, 1));
        graph[6].add(new Edge(6, 8));
        graph[5].add(new Edge(5, 8));

        
    }
    public static void bfs(ArrayList<Edge>[]graph){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                bfsUtil(graph, vis);
            }
        }
    }
    public static void bfsUtil(ArrayList<Edge>[]graph,boolean vis[]){
        Queue<Integer>q=new LinkedList<>();
        q.add(0);
        while(!q.isEmpty()){
            int curr=q.remove();
            if(!vis[curr]){
                System.out.print(curr+" ");
                vis[curr]=true;
                for(int i=0;i<graph[curr].size();i++){
                    Edge e=graph[curr].get(i);
                    if(!vis[e.dest]){
                        q.add(e.dest);
                    }
                }
            }
        }
        System.out.println();
    }
    public static void dfs(ArrayList<Edge>[]graph){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                dfsUtil(graph, vis, i);
            }
        }
    }
    public static void dfsUtil(ArrayList<Edge>[]graph,boolean vis[],int curr){
        System.out.print(curr+" ");
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                dfsUtil(graph, vis, e.dest);
            }
        }
    }
    public static boolean hasPath(ArrayList<Edge>[]graph,int src,int dest,boolean vis[]){
        if(src==dest){
            return true;
        }
        vis[src]=true;
        for(int i=0;i<graph[src].size();i++){
            Edge e=graph[src].get(i);
            if(!vis[e.dest] && hasPath(graph, e.dest, dest,vis)){
                return true;
            }
        }
        return false;
    }
    public static boolean dectectCycle(ArrayList<Edge>[]graph){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                dectectCycleUtil(graph,vis,i,-1);
                return true;
            }
        }
        return false;
    }
    public static boolean dectectCycleUtil(ArrayList<Edge>graph[],boolean vis[],int curr,int par){
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                if(dectectCycleUtil(graph, vis, e.dest, curr)){
                    return true;
                }

            }
            else if(vis[e.dest] && e.dest!=par){
                return true;
            }
        }
        return false;
    }
    public static boolean isBipartite(ArrayList<Edge>graph[]){
        int col[]=new int[graph.length];
        for(int i=0;i<col.length;i++){
            col[i]=-1;
        }
        Queue<Integer>q=new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(col[i]==-1){
                col[i]=0;
                q.add(i);
                while(!q.isEmpty()){
                    int curr=q.remove();
                    for(int j=0;j<graph[curr].size();j++){
                        Edge e=graph[curr].get(j);
                        if(col[e.dest]==-1){
                            int nextCol=col[curr]==0 ? 1:0;
                            col[e.dest]=nextCol;
                            q.add(e.dest);
                        }else if(col[e.dest]==col[curr]){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public static boolean isCycle(ArrayList<Edge>graph[]){
        boolean vis[]=new boolean[graph.length];
        boolean stack[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                isCycleUtil(graph,i,vis,stack);
                return true;
            }
        }
        return false;
    }
    public static boolean isCycleUtil(ArrayList<Edge>graph[],int curr,boolean vis[],boolean stack[]){
        vis[curr]=true;
        stack[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(stack[e.dest]){
                return true;
            }else if(!vis[e.dest] && isCycleUtil(graph, e.dest, vis, stack)){
                return true;
            }
        }
        stack[curr]=false;
        return false;
    }
    public static void topSort(ArrayList<Edge>[]graph){
        boolean[]vis=new boolean[graph.length];
        Stack<Integer>s=new Stack<>();
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                topSortUtil(graph,i,vis,s);
            }
        }
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }System.out.println();
    }
    public static void topSortUtil(ArrayList<Edge>[]graph,int curr,boolean[]vis, Stack<Integer>s){
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e =graph[curr].get(i);
            if(!vis[e.dest]){
                topSortUtil(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }
    public static void calcIndeg(ArrayList<Edge>graph[],int  indeg[]){
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].size();j++){
                Edge e=graph[i].get(j);
                indeg[e.dest]++;
            }
        }
    }
    public static void topSortBfs(ArrayList<Edge>graph[]){
        int indeg[]=new int[graph.length];
        calcIndeg(graph, indeg);
        Queue<Integer>q=new LinkedList<>();
        for(int i=0;i<indeg.length;i++){
            if(indeg[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int curr=q.remove();
            System.out.print(curr+" ");
            for(int i=0;i<graph[curr].size();i++){
                Edge e=graph[curr].get(i);    
                indeg[e.dest]--;
                if(indeg[e.dest]==0){
                    q.add(e.dest);
                }
            }
        }
    }
    public static void printAllPath(ArrayList<Edge>graph[],int src,int dest,String path){
        if(src==dest){
            System.out.println(path+dest);
            return;
        }
        for(int i=0;i<graph[src].size();i++){
            Edge e=graph[src].get(i);
            printAllPath(graph, e.dest, dest, path+src);
        }
    }
    static class Edge2{
        int src;
        int dest;
        int weight;
        public Edge2(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.weight=w;
        }
    }
    static class Pair implements Comparable<Pair>{
        int v;
        int cost;
        public Pair(int v,int cost){
            this.v=v;
            this.cost=cost;
        }
        @Override
        public int compareTo(Pair p2){
            return this.cost-p2.cost;
        }
    }
    public static void prims(ArrayList<Edge2>[]graph){
        boolean vis[]=new boolean[graph.length];
        PriorityQueue<Pair>pq=new PriorityQueue<>();
        int finalCost=0;
        pq.add(new Pair(0, 0));
        while(!pq.isEmpty()){
            Pair curr=pq.remove();
            if(!vis[curr.v]){
                vis[curr.v]=true;
                finalCost+=curr.cost;
                for(int i=0;i<graph[curr.v].size();i++){
                    Edge2 e=graph[curr.v].get(i);
                    pq.add(new Pair(e.dest, e.weight));
                }

            }
        }
        System.out.println("final cost: "+finalCost);
    }
    public static boolean isCycleBfs(ArrayList<Edge>[]graph){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                if(isCycleUtilBfs(graph,i,-1,vis)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isCycleUtilBfs(ArrayList<Edge>[]graph,int start,int par,boolean []vis){
        Queue<Integer>q=new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            int curr=q.remove();
            if(!vis[curr]){
                vis[curr]=true;
                for (Edge e : graph[curr]) {
                    int w=e.dest;
                    if(!vis[w]){
                        q.add(w);
                    }else if(w!=par){
                        return true;
                    }
                }
                }
            }
        
        return false;
    }
    public static boolean isCycleBfs1(ArrayList<Edge>[]graph){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                if(isCycleUtilBfs1(graph,i,-1,vis)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isCycleUtilBfs1(ArrayList<Edge>[]graph,int start,int par,boolean []vis){
        Queue<Integer>q=new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            int curr=q.remove();
            if(!vis[curr]){
                vis[curr]=true;
                for (Edge e : graph[curr]) {
                    int w=e.dest;
                    if(!vis[w]){
                        q.add(w);
                    }else if(w!=par){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data=data;
            right=null;
            left=null;
            size++;
        }
    }
    public static int size=0;
    
    public static void minimun_Height(ArrayList<Edge>[]graph,boolean vis[],int curr){
        int h=0;
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                h++;
                dfsUtil(graph, vis, e.dest);
            }
        }
        System.err.println(h);
    }
    
    
    public static void main(String[] args) {
        int V=6;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[]graph=new ArrayList[V];
        Node root=new Node(1);
        root.left=new Node(8);
        root.right=new Node(2);
        root.left.left=new Node(6);
        root.left.right=new Node(5);
        root.right.left=new Node(9);
        boolean vis[]=new boolean[V];
        createdGraph(graph);
        dfs(graph);
        // minimun_Height(graph, vis, 0);

    


    }
}
