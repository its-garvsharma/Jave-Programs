package Binary_Tree;
import java.util.*;
public class Binaryprc {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data =data;
            this.left=null;
            this.right=null;
        }
    }
    public class BinaryTree{
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
            return ;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    public static void postorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        inorder(root.right);
        System.out.print(root.data+" ");
    }
    public static void levelOrder(Node root){
        if(root==null){
            return;
        }
        Queue<Node>q=new LinkedList<>();
        while(!q.isEmpty()){
            Node currNode=q.remove();
            if(currNode==null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            }else{
                System.out.println(currNode.data+" ");
                if(currNode.left!=null){
                    q.add(currNode.left);
                }
                if(currNode.right!=null){
                    q.add(currNode.right);
                }
            }
        }
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
        int leftcount=count(root.left);
        int rigthcount=count(root.right);
        
        return leftcount+rigthcount+1;
    }
    public static int sumOfNode(Node root){
        if(root==null){
            return 0;
        }
        int leftsum=sumOfNode(root.left);
        int rightsum=sumOfNode(root.right);
        return leftsum+rightsum+root.data;
    }
    

public static int diameter1(Node root){
        if(root==null){
            return 0;
        }
        int ldia=diameter1(root.left);
        int rdia=diameter1(root.right);
        int lh=height(root.left);
        int rh=height(root.right);
        int selfdia=lh+rh+1;
        return Math.max(Math.max(rdia, ldia), selfdia);
}
    static class Info{
        int diam;
        int ht;
        Info(int diam,int ht){
            this.diam=diam;
            this.ht=ht;
        }
    }
    public static Info diameter(Node root){
        if(root==null){
            return new Info(0, 0);
        }
        Info leftInfo=diameter(root.left);
        Info rightInfo=diameter(root.right);
        int dia=Math.max(leftInfo.diam, Math.max(leftInfo.ht+rightInfo.ht+1, rightInfo.diam));
        int height=Math.max(leftInfo.ht, rightInfo.ht)+1;
        
        return new Info(dia, height);
    }
    public static boolean isIdentical(Node root,Node subRoot){
        if(root==null&& subRoot==null){
            return true;
        }else if(root==null||subRoot==null||root.data!=subRoot.data){
            return false;
        }
        if(root.left!=subRoot.left){
            return false;
        }
        if(root.right!=subRoot.right){
            return false;
        }
        return true;
    }
    public static boolean isSubTree(Node root,Node subRoot){
        if(root==null && subRoot==null){
            return true;
        }
        if(root.data==subRoot.data){
            if(isIdentical(root, subRoot)){
                return true;
            }
        }
        return isSubTree(root.left, subRoot)||isSubTree(root.right, subRoot);
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
        if(root==null){
            return;
        }
        Queue<Info1>q=new LinkedList<>();
        HashMap<Integer,Node>map=new HashMap<>();
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
            }else{
                if(!map.containsKey(curr.hd)){
                    map.put(curr.hd, curr.node);
                }
                if(curr.node.left!=null){
                    q.add(new Info1(curr.node.left,curr.hd-1));
                    min=Math.min(min,curr.hd-1);
                }
                if(curr.node.right!=null){
                    q.add(new Info1(curr.node.right,curr.hd+1));
                    max=Math.max(max,curr.hd+1);
                }
            }
        }
        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data+" ");
        }System.out.println();
    }
    public static boolean isUnivalued(Node root){
        if(root==null){
            return true;
        }
        int newleft=0;
        int newright=0;
        if(root.left!=null){
            newleft=root.left.data;
        }
        if(root.right!=null){
            newright=root.right.data;
        }
        if(root==null ||newleft!=newright){
            return false;
        }
        if(!isUnivalued(root.left)){
            return false;
        }
        if(!isUnivalued(root.right)){
            return false;
        }
        return true;
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
    public static void allduplicate(Node root){
        if(root==null){
            return;
        }
        ArrayList<Integer>l=new ArrayList<>();
        l.add(root.data);
        if(root.left!=null){
            l.add(root.left.data);
        }
        if(root.right!=null){
            l.add(root.right.data);
        }
        allduplicate(root.left);
        allduplicate(root.right);
        for(int i=0;i<l.size();i++){
            int count=0;
            for(int j=0;j<l.size();j++){
                if(l.get(i)==l.get(j)){
                    count++;
                }
            }
            if(count>1){
                System.out.print(l.get(i)+" ");
            }
        }

    }

    
    public static void main(String[] args) {
        Node root=new Node(1);
        root.left=new Node(4);
        root.right=new Node(3);
        root.left.left=new Node(3);
        root.left.right=new Node(3);
        root.right.left=new Node(4);
        root.right.right=new Node(6);
        allduplicate(root);
        
        
    }
    public static Node deletLeafNode(Node root,int key){
        if(root==null){
            return root;
        }
        if(root.left==null&&root.right==null&&root.data==key){
            root = null;
            return root;
        }
        root.left= deletLeafNode(root.left, key);
        root.right=deletLeafNode(root.right, key);
        return root;
    }
}