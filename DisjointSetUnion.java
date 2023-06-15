package Graphs;

import java.util.ArrayList;
import java.util.*;

public class DisjointSetUnion {
    static int n=7;//vertices
    static int par[]=new int[n];
    static int rank[]=new int[n];

    public static void init(){
        for(int i=0;i<n;i++){
            par[i]=i;
        }
    }
    public static int find(int x){
        if(x==par[x]){
            return x;
        }
        return par[x]=find(par[x]);
    }
    public static void union(int a,int b){
        int parA=find(a);
        int parB=find(b);
        if(rank[parA]==rank[parB]){
            par[parB]=parA;
            rank[parA]++;
        }else if(rank[parA]<rank[parB]){
            par[parA]=parB;
        }else{
            par[parB]=parA;
        }
    }
    static class Edge implements Comparable<Edge>{
        int src,dest,wt;
        public Edge(int s,int d,int w){
            this.src=s;
            this.dest=d;
            this.wt=w;
        }
        @Override
        public int compareTo(Edge e2){
            return this.wt-e2.wt;
        }
    }
    public static void createdGraph(ArrayList<Edge>edges){
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 15));
        edges.add(new Edge(0, 3, 30));
        edges.add(new Edge(1, 3, 40));
        edges.add(new Edge(2,3, 50));
    }
    public static void kruskalsMST(ArrayList<Edge> edge,int V){
        init();
        Collections.sort(edge);
        int mastCost=0;
        int count=0;

        for(int i=0;count<V-1;i++){
            Edge e=edge.get(i);
            int parA=find(e.src);
            int parB=find(e.dest);
            if(parA!=parB){
                union(e.src, e.dest);
                mastCost+=e.wt;
                count++;
            }
        }
        System.out.println(mastCost);
    }
    public static void helper(int [][]image,int sr,int sc,int color,boolean[][]vis,int orgCol){
        if(sr<0 ||sc<0||sr>=image.length||sc>=image[0].length
            || vis[sr][sc]||image[sr][sc]!=orgCol){
                return;
            }
        // left
        helper(image, sr, sc-1, color, vis, orgCol);
        // right
        helper(image, sr, sc+1, color, vis, orgCol);
        // up
        helper(image, sr-1, sc, color, vis, orgCol);
        // down
        helper(image, sr+1, sc, color, vis, orgCol);
    }
    public static int[][] floodFill(int [][]image,int sr,int sc,int color){
        boolean vis[][]=new boolean[image.length][image[0].length];
        helper(image,sr,sc,color,vis,image[sr][sc]);
        return image;
    }   
    public static void main(String[] args) {
        int V=4;
        ArrayList<Edge>edges=new ArrayList<>();
        createdGraph(edges);
        kruskalsMST(edges, V);
        int image[][]={{0,0,0},{0,0,0}};
        for(int i=1;i<image.length;i++){
            for(int j=0;j<image[0].length;j++){
                if(image[i][j]==1){
                    image[i][j]=2;
                }if(image[i][j]==0){
                    // continue;
                    break;
                }
            }
        }
        for(int i=0;i<image.length;i++){
            for(int j=0;j<image[0].length;j++){
                System.out.print(image[i][j]+" ");
            }System.out.println();
        }

    }   
}
