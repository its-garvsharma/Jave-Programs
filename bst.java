package BST;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.xml.crypto.Data;



public class bst {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=this.right=null;
        }
    }
    public static Node insert(Node root,int val){
        if(root==null){
            root=new Node(val);
            return root;
        }
        if(root.data>val){
            root.left=insert(root.left, val);
        }if(root.data<val){
            root.right=insert(root.right, val);
        }
        return root;
    }
    public static boolean search(Node root,int key){
        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }
        if(root.data>key){
            return search(root.left, key);
        }else{
            return search(root.right, key);
        }
    }
    public static Node delete(Node root,int val){
        if(root.data<val){
            root.right=delete(root.right, val);
        }else if(root.data>val){
            root.left=delete(root.left, val);
        }else{
            if(root.left==null && root.right==null){
                return null;
            }else if(root.left==null){
                return root.right;
            }else if(root.right==null){
                return root.left;
            }
            Node IS=findInorderSuccessor(root.right);
            root.data=IS.data;
            root.right=delete(root.right, IS.data);
        }
        return root;
    }
    public static void range(Node root,int k1,int k2){
        if(root==null){
            return ;
        }
        if(root.data>=k1 && root.data<=k2){
            System.out.print(root.data+" ");
        }
        range(root.left, k1, k2);
        range(root.right, k1, k2);
    }
    public static Node findInorderSuccessor(Node root){
        if(root.left!=null){
            root=root.left;
        }
        return root;
    }
    
    public static void printPath(ArrayList<Integer>path){
        for(int i=0;i<path.size();i++){
            System.out.print(path.get(i)+" ");
        }System.out.println();
    }
    public static void rootToLeafPath(Node root,ArrayList<Integer>path){
        if(root==null){
            return;
        }
        path.add(root.data);
        if(root.right==null &&root.left==null){
            printPath(path);
        }
        rootToLeafPath(root.left, path);
        rootToLeafPath(root.right, path);
        path.remove(path.size()-1);
    }
    public static int sum=0;
    public static void sum_of_range(Node root,int l,int r){
        if(root==null){
            return;
        }
        ArrayList<Integer>arr=new ArrayList<>();
        sum_of_range(root.left,l,r);
        arr.add(root.data);
        sum_of_range(root.right,l,r);
        for(int i=0;i<arr.size();i++){
            if(arr.get(i)>=l && arr.get(i)<=r){
                sum+=arr.get(i);
            }
        }
    }
    public static boolean isValidBST(Node root,Node min,Node max){
        if(root==null){
            return true;
        }
        if(min != null && min.data>root.data){
            return false;
        }
        if(max!=null && max.data<root.data){
            return false;
        }
        return isValidBST(root.left, min, max) && isValidBST(root.right, min, max);
        
    }
    public static int sumrange(Node root,int l,int r){
        if(root==null){
            return 0;
        }
        int ans1=sumrange(root.left, l, r);
        int ans2=sumrange(root.right, l, r);
        if(root.data>=l && root.data<=r){
            return ans1+ans2+root.data;
        }
        return ans1+ans2;
    }
    static int minidiff=Integer.MAX_VALUE;
    static int min=0;
    public static void closestElement(Node root,int k){
        if(root==null){
            return;
        }
        if(Math.abs(root.data-k)<minidiff){
            minidiff=Math.abs(root.data-k);
            min=root.data;
        }
        if(k>root.data){
            closestElement(root.right, k);  
        }else{
            closestElement(root.left, k);
        }
        
    }
    static int kth=0;
    public static void KthsmallestElement(Node root,int k){
        if(root==null){
            return;
        }
        ArrayList<Integer>arr=new ArrayList<>();
        KthsmallestElement(root.left, k);
        arr.add(root.data);
        KthsmallestElement(root.right, k);

        for(int i=0;i<arr.size();i++){
            if(i==k-1){
                kth=arr.get(i+1);
                break;
            }
        }
    }
    public static Node invert(Node root){
        if(root==null){
            return null;
        }
        Node temp=root.right;
        root.right=root.left;
        root.left=temp;
        invert(root.left);
        invert(root.right);
        return root;
    }
    public static void preorder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }
    public static int transform(Node root){
        if(root==null){
            return 0;
        }
        int leftchild=transform(root.left);
        int rightchild=transform(root.right);
        int data=root.data;
        int newleft=root.left==null? 0: root.left.data;
        int newright=root.left==null? 0: root.right.data;
        root.data= leftchild+rightchild+newleft+newright;
        return data;
    }
    public static Node createBST(int arr[],int si,int ei){
        if(si>ei){
            return null;
        }
        int mid=(si+ei)/2;
        Node root=new Node(arr[mid]);
        root.left=createBST(arr, si, mid-1);
        root.right=createBST(arr, mid+1, ei);
        return root;
    }
    public static Node createBST(ArrayList<Integer>arr,int si,int ei){
        if(si>ei){
            return null;
        }
        int mid=(si+ei)/2;
        Node root=new Node(arr.get(mid));
        root.left=createBST(arr, si, mid-1);
        root.right=createBST(arr, mid+1, ei);
        return root;
    }
    public static Node balancedbst(Node root){
        if(root==null){
            return null;
        }
        ArrayList<Integer>arr=new ArrayList<>();
        balancedbst(root.left);
        arr.add(root.data);
        balancedbst(root.right);
        createBST(arr, 0, arr.size()-1);
        return root;
    }
    
    public static void main(String[] args) {
        Node root=new Node(8);
        root.left=new Node(5);
        root.left.left=new Node(3);
        root.left.right=new Node(6);
        root.right=new Node(11);
        root.right.right=new Node(20);
        
        int arr[]={3,5,6,8,10,11,12};
        
        preorder(createBST(arr, 0, arr.length-1));
        

        
        

        // KthsmallestElement(root, 3);
        // System.out.println(kth);

        
    }
}
