package Graphs;
import java.util.*;
public class Graph2 {
    static class Edge{
        int src;
        int dest;

        public Edge(int s,int d){
            this.src=s;
            this.dest=d;
        }
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
        Queue<Integer> q=new LinkedList<>();
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
    
    public static void dfs(ArrayList<Edge>graph[]){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            dfsUtil(graph, i, vis);
        }
    }
    
    public static void dfsUtil(ArrayList<Edge>[]graph,int curr,boolean vis[]){
        // visit
        System.out.print(curr+" ");
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                dfsUtil(graph, curr, vis);
            }
        }
    }
    static void createdGraph(ArrayList<Edge>[]graph){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }   

            graph[0].add(new Edge(0, 2));            
            graph[0].add(new Edge(0, 3));            
            graph[1].add(new Edge(1, 0));            
            graph[2].add(new Edge(2, 1));            
            graph[3].add(new Edge(3, 4));            
                     
    }
    public static boolean dectectCycle(ArrayList<Edge>[]graph){
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){

                if(dectectCycleUtil(graph,vis,i,-1)){
                    return true;
                    // cycle exists in one of the parts
                }
            }
        }
        return false;
    }
    public static boolean dectectCycleUtil(ArrayList<Edge>[]graph, boolean vis[],int curr,int par){
        vis[curr]=true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            // case 3
            if(!vis[e.dest]){
                if(dectectCycleUtil(graph, vis, e.dest, curr)){
                    return true;
                }
            }
            //case 1 
            else if(vis[e.dest] && e.dest !=par){
                return true;
            }
            // cas2 -> do nothing -> continue
        }
        return false;
    }
    public static boolean isBipartite(ArrayList<Edge>[] graph){
        int col[]=new int[graph.length];
        for(int i=0;i<col.length;i++){
            col[i]=-1;
        }
        Queue<Integer>q=new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(col[i]==-1){
                q.add(i);
                col[i]=0;//yellow
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
    public static boolean ifCycle(ArrayList<Edge>[] graph){
        boolean vis[]=new boolean[graph.length];
        boolean stack[]=new boolean[graph.length];

        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                if(isCycleUtil(graph,i,vis,stack)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isCycleUtil(ArrayList<Edge>[] graph,int curr,boolean vis[],boolean stack[]){
        vis[curr]=true;
        stack[curr]=true;

        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(stack[e.dest]){
                return true;
            }
            if(!vis[e.dest] && isCycleUtil(graph, e.dest, vis, stack)){
                return true;
            }
        }
        stack[curr]=false;
        return false;
    }
    public static void topSort(ArrayList<Edge>[] graph){
        boolean vis[]=new boolean[graph.length];
        Stack<Integer> s=new Stack<>();
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                topSortUtil(graph,i,vis,s);
            }
        }
        while(!s.empty()){
            System.out.print(s.pop()+" ");
        }System.err.println();
    }
    public static void topSortUtil(ArrayList<Edge>[] graph,int curr,boolean vis[],Stack<Integer>s){
        vis[curr]=true;

        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                topSortUtil(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }
    public static void calcIndeg(ArrayList<Edge> graph[],int indeg[]){
        for(int i=0;i<graph.length;i++){
            int v=i;
            for(int j=0;j<graph[v].size();j++){
                Edge e =graph[v].get(j);
                indeg[e.dest]++;
            }
        }
    }
    public static void topSortBfs(ArrayList<Edge>[]graph){
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
                Edge e= graph[curr].get(i);
                indeg[e.dest]--;
                if(indeg[e.dest]==0){
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }
    public static void printAllPath(ArrayList<Edge>[]graph,int src,int dest,String path){
        if(src==dest){
            System.out.println(path+dest);
            return;
        }
        for(int i=0;i<graph[src].size();i++){
            Edge e=graph[src].get(i);
            printAllPath(graph, e.dest, dest, path+src);
        }
    }
   
    public static void main(String[] args) {
        int V=6;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>graph[]=new ArrayList[V];
        createdGraph(graph);
        int src=5,dest=1;
        printAllPath(graph, src, dest, "");
    }
}
