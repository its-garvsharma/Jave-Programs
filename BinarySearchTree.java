package BST;

import java.sql.RowId;
import java.util.*;
public class BinarySearchTree {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static Node insert(Node root,int val){
        if(root==null){
            root=new Node(val);
            return root;
        }
        if(root.data>val){
            // left subtree
            root.left=insert(root.left, val);
        }
        if(root.data<val){
            // right subtree
            root.right=insert(root.right, val);
        }
        return root;
    }
    public static void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    public static boolean searchKey(Node root,int key){
        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }
        if(root.data>key){
           return searchKey(root.left, key);
        }
        else{
            return searchKey(root.right, key);
        }
    }
    public static Node delete(Node root,int val){
        if(root.data < val){
            root.right=delete(root.right,val);
        }
        else if(root.data >val){
            root.left=delete(root.left, val);
        }
        else{
            //voila
            // Case 1 -leaf Node
            if(root.left==null && root.right==null){
                return null;
            }
            // Case 2-single child
            if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }
            // Case 3 -both children
            Node IS=findInorderSuccessor(root.right);
            root.data=IS.data;
            root.right=delete(root.right, IS.data);
        }
        return root;
    }
    public static Node findInorderSuccessor(Node root){
        while(root.left !=null){
            root=root.left;
        }
        return root;
    }
    public static void range(Node root,int k1,int k2){
        while(root ==null){
            return;
        }
        if(root.data>=k1 &&root.data<=k2){
            
            System.out.print(root.data+" ");
        }
        range(root.left, k1, k2);
        range(root.right, k1, k2);
    }
    public static void printPath(ArrayList<Integer>path){
        for(int i=0;i<path.size();i++){
            System.out.print(path.get(i)+"-> ");
        }System.out.println("null");
    }
    public static void rootToLeafPath(Node root,ArrayList<Integer>path){
        if(root==null){
            return;
        }
        path.add(root.data);
        if(root.left==null && root.right==null){
            printPath(path);
        }
        rootToLeafPath(root.left, path);
        rootToLeafPath(root.right, path);
        path.remove(path.size()-1);
    }
    public static boolean isValidBST(Node root,Node min,Node max ){
        if(root==null){
            return true;
        }
        if(min!=null && root.data<= min.data){
            return false;
        }
        else if(max!= null && root.data >=max.data){
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
    public static Node createMirror(Node root){
        if(root==null){
            return null;
        }
        Node leftMirror=createMirror(root.left);
        Node rightMirror=createMirror(root.right);

        root.left=rightMirror;
        root.right=leftMirror;
        return root;
    }
    public static void preoder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preoder(root.left);
        preoder(root.right);
    }
    public static void invert(Node root){
        if(root==null){
            return;
        }
        Node temp=root.right;
        root.right=root.left;
        root.left=temp;
        invert(root.left);
        invert(root.right);
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
    
    public static Node convertBST_To_BalncedBST(Node root){
        if(root==null){
            return null;
        }
        ArrayList<Integer>bst=new ArrayList<>();
        convertBST_To_BalncedBST(root.left);
        bst.add(root.data);
        convertBST_To_BalncedBST(root.right);
        createBST(bst, 0, bst.size()-1);
        return root;
    }
    static class Info{
        boolean isBST;
        int size;
        int min;
        int max;
        public Info(boolean isBST,int size,int min,int max){
            this.isBST=isBST;
            this.size=size;
            this.min=min;
            this.max=max;
        }
    }
    public static int maxBST=0;
    public static Info largestBST(Node root){
        if(root==null){
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Info leftInfo=largestBST(root.left);
        Info rightInfo=largestBST(root.right);
        int size=leftInfo.size + rightInfo.size+1;
        int min=Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        int max=Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));
        if(root.data <=leftInfo.max  || root.data>=rightInfo.min){
            return new Info(false, size, min, max);
        }
        if(leftInfo.isBST && rightInfo.isBST){
            maxBST=Math.max(maxBST, size);
            return new Info(true, size, min, max);
        }
        return new Info(false, size, min, max);
    }
    public static void getInorder(Node root,ArrayList<Integer>arr){
        if(root==null){
            return;
        }
        getInorder(root.left, arr);
        arr.add(root.data);
        getInorder(root.right, arr);
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
    public static Node mergeBSTs(Node root1,Node root2){
        // step 1
        ArrayList<Integer>arr1=new ArrayList<>();
        getInorder(root1,arr1);
        // step2
        ArrayList<Integer>arr2=new ArrayList<>();
        getInorder(root2,arr2);
        // merge
        int i=0,j=0;
        ArrayList<Integer>finalArr=new ArrayList<>();
        while(i<arr1.size() && j<arr2.size()){
            if(arr1.get(i)<=arr2.get(j)){
                finalArr.add(arr1.get(i));
                i++;
            }else{
                finalArr.add(arr2.get(j));
                j++;
            }
        }
        
        while(i<arr1.size()){
            finalArr.add(arr1.get(i));
            i++;
        }
        while(j<arr2.size()){
            finalArr.add(arr2.get(j));
            j++;
        }

        // sorted al->Balace BST
        return createBST(finalArr, 0, finalArr.size()-1);

    }

    public static void main(String[] args) {
        Node root1=new Node(2);
        root1.left=new Node(1);
        root1.right=new Node(4);

        Node root2=new Node(9);
        root2.left=new Node(3);
        root2.right=new Node(12);
        preoder(mergeBSTs(root1, root2));
        
        
    }
}
