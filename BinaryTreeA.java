package Binary_Tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import javax.xml.crypto.Data;

public class BinaryTreeA {
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
    static class BinaryTree{
        static int idx=-1;
        public static Node buildTree(int node[]){
            idx++;
            if(node[idx]==-1){
                return null;
            }
            Node newNode=new Node(node[idx]);
            newNode.left=buildTree(node);
            newNode.right=buildTree(node);

            return newNode;
        }
        public static void preorder(Node root){
            if(root==null){
                return;
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }
        public static void inorder(Node root){
            if(root==null){
                return;
            }
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }   
        public static void postorder(Node root){
            if(root==null){
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data+" ");
        }
        public static void levelOrder(Node root){
            if(root==null){
                return;
            }
            Queue <Node>q=new LinkedList<>();
            q.add(root);
            q.add(null);
            while(!q.isEmpty()){
                Node currNode= q.remove();
                if(currNode==null){
                    System.out.println();
                    if(q.isEmpty()){
                        break;
                    }else{
                        q.add(null);
                    }
                }else{
                    System.out.print(currNode.data+" ");
                    if(currNode.left!=null){
                        q.add(currNode.left);
                    }
                    if(currNode.right!=null){
                        q.add(currNode.right);
                    }
                }
            }
        }
           
    }
    public static int diameter2(Node root){
            if(root==null){
                return 0;
            }
            int ldia=diameter2(root.left);
            int rdia=diameter2(root.right);
            int lh=height(root.left);
            int rh=height(root.right);
            int selfdia=lh+rh+1;
            return Math.max(selfdia, Math.max(rdia, ldia));
    }
    public static int height(Node root){
        if(root==null){
            return 0;
        }
        int lh=height(root.left);
        int rh=height(root.right);
        return Math.max(lh, rh)+1;
    }
    public static int count(Node root){
        if(root==null){
            return 0;
        }
        int lcount=count(root.left);
        int rcount=count(root.right);
        return lcount+rcount+1;

    }
    public static int sumOfNode(Node root){
        if(root==null){
            return 0;
        }
        int leftsum=sumOfNode(root.left);
        int rightsum=sumOfNode(root.right);
        return leftsum+rightsum+root.data;
    }
    static class Info{
        int diam;
        int ht;
        
        public Info(int diam,int ht){
            this.diam=diam;
            this.ht=ht;
        }
    }
    public static Info diameter(Node root){
        if(root==null){
            return new Info(0,0);
        }
        Info leftInfo=diameter(root.left);
        Info rigthInfo=diameter(root.right);

        int diam=Math.max(Math.max(leftInfo.diam, rigthInfo.diam),leftInfo.ht+rigthInfo.ht+1);
        int ht=Math.max(leftInfo.ht, rigthInfo.ht) +1;
        return new Info(diam, ht);
    }
    public static boolean isIdentical(Node node,Node subRoot){
        if(node ==null && subRoot==null){
            return true;
        }else if(node==null || subRoot==null||node.data!=subRoot.data){
            return false;
        }
        if(!isIdentical(node.left, subRoot.left)){
            return false;
        }
        if(!isIdentical(node.right, subRoot.right)){
            return false;
        }
        return true;
    }
    public static boolean isSubTree(Node root,Node subRoot){
        if(root==null){
            return false;
        }
        
        if(root.data==subRoot.data){
            if(isIdentical(root,subRoot)){
                return true;
            }
        }
        return isSubTree(root.left, subRoot)||isSubTree(root.right, subRoot) ;
    }
    static class Info1{
        Node node;
        int hd;

        public Info1(Node node,int hd){
            this.node=node;
            this.hd=hd;
        }
    }
    public static void topView(Node root){
        // level Order
        Queue<Info1>q=new LinkedList<>();
        HashMap<Integer,Node> map=new HashMap<>();
        int min=0,max=0;
        q.add(new Info1(root, 0));
        q.add(null);

        while(!q.isEmpty()){
            Info1 curr=q.remove();
            if(curr==null){
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }

            else{
                if(!map.containsKey(curr.hd)){ //first time my hd is occuring
                    map.put(curr.hd, curr.node);
                }
                if(curr.node.left!=null){
                    q.add(new Info1(curr.node.left, curr.hd-1));
                    min=Math.min(min, curr.hd-1);
                }
                if(curr.node.right!=null){
                    q.add(new Info1(curr.node.right, curr.hd+1));
                    max=Math.max(max, curr.hd+1);
            }
        }
        }
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data+" ");
        }System.out.println();
    }
    public static void klevel(Node root,int level,int k){
        if(root==null){
            return;
        }
        if(level==k){
            System.out.print(root.data+" ");
        }
        klevel(root.left, level+1, k);
        klevel(root.right, level+1, k);
    }
    public static boolean getPath(Node root,int n,ArrayList<Node> path){
        if(root==null){
            return false;
        }
        path.add(root);
        if(root.data==n){
            return true;
        }
        boolean foundleft=getPath(root.left, n, path);
        boolean foundright=getPath(root.right, n, path);

        if(foundleft||foundright){
            return true;
        }
        path.remove(path.size()-1);
        return false;
    }
    public static Node lca(Node root,int n1,int n2){
        ArrayList<Node>path1=new ArrayList<>();
        ArrayList<Node>path2=new ArrayList<>();
        
        getPath(root,n1,path1);
        getPath(root,n2,path2);
        // last common ancestor
        int i=0;
        for(;i<path1.size()&&i<path2.size();i++){
            if(path1.get(i)!=path2.get(i)){
                break;
            }
        }
        // last equal node ->i-1th
        Node lca=path1.get(i-1);
        return lca;

    }
    public static Node lca2(Node root,int n1,int n2){
        if(root==null){
            return null;
        }
        if(root.data==n1||root.data==n2){
            return root;
        }
        Node leftLca=lca2(root.left, n1, n2);
        Node rightLca=lca2(root.right, n1, n2);

        // leftLCA=val rightLca=null
        if(rightLca==null){
            return leftLca;
        }
        if(leftLca==null){
            return rightLca;
        }
        return root;
        // if(leftLca!=null && rightLca!=null){
        //     return root;
        // }
        // return (leftLca!=null) ? leftLca:rightLca;
    
    }
    public static int lcaDist(Node root,int n){
        if(root==null){
            return -1;
        }
        if(root.data==n){
            return 0;
        }
        int leftDist=lcaDist(root.left, n);
        int rightDist=lcaDist(root.right, n);
        if(leftDist==-1 && rightDist==-1){
            return -1;
        }else if(leftDist==-1){
            return rightDist+1;
        }else{
            return leftDist+1;
        }
    }
    public static int minDist(Node root,int n1,int n2){
        Node lca=lca2(root, n1, n2);
        int dist1=lcaDist(root,n1);
        int dist2=lcaDist(root,n2);

        return dist1+dist2;
    }
    public static int KAncestor(Node root,int n,int k){
        if(root==null){
            return -1;
        }
        if(root.data==n){
            return 0;
        }
        int leftDist=KAncestor(root.left, n, k);
        int rightDist=KAncestor(root.right, n, k);

        if(leftDist==-1&& rightDist==-1){
            return -1;
        }
        int max=Math.max(leftDist, rightDist);
        if(max+1 ==k){
            System.out.println(root.data);
        }
        return max+1;
    }
    public static int transform(Node root){
        if(root==null){
            return 0;
        }
        int leftChild=transform(root.left);
        int rightChild=transform(root.right);
        int data=root.data;
        int newleft=root.left==null ? 0: root.left.data;
        int newright=root.right==null ? 0: root.right.data;
        root.data=newleft+leftChild+newright+rightChild;
        return data;
    }
    public static void preorder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
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
    
    public static void main(String[] args) {
        /*
                1
               / \
              2   3
             / \ / \
             4 5 6 7 
         */
        Node root=new Node(8);
        root.left=new Node(5);
        root.right =new Node(10);
        root.left.left=new Node(3);
        root.left.right=new Node(6);
        root.right.left=new Node();
        root.right.right=new Node(11);
        invert(root);
        // transform(root);
        preorder(root);
    }  

}
