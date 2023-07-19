package Trie;
public class Tries {
    static class Node{
        Node children[]=new Node[26];
        boolean eow=false;
        Node(){
            for(int i=0;i<26;i++){
                children[i]=null;
            }
        }
    }
    public static Node root= new Node();
    public static void insert(String word){//O(l)
        Node curr=root;
        for(int level=0;level<word.length();level++){
            int idx=word.charAt(level)-'a';
            if(curr.children[idx]==null){
                curr.children[idx]=new Node();
            }
            curr=curr.children[idx];
        }
        curr.eow=true;
    }
    public static boolean search(String key){
        Node curr=root;
        for(int level=0;level<key.length();level++){
            int idx=key.charAt(level)-'a';
            if(curr.children[idx]==null){
                return false;
            }
            curr=curr.children[idx];
        }
        return curr.eow=true;
    }
    public static boolean wordBreak(String key){
        if(key.length()==0){
            return true;
        }
        for(int i=1;i<=key.length();i++){
            // substring(beg idx,last indx)(0,1)
            if(search(key.substring(0, i)) &&
             wordBreak(key.substring(i))){
                return true;
            }
        }
        return false;
    }
    public static int countNode(Node root){
        if(root==null){
            return 0;
        }
        int count=0;
        for(int i=0;i<root.children.length;i++){
            if(root.children[i]!=null){
                count+=countNode(root.children[i]);
            }
        }
        return count+1;
    }
    public static String ans="";
    public static void longestWord(Node root,StringBuilder temp){
        if(root==null){
            return;
        }
        for(int i=0;i<26;i++){
            if(root.children[i]!=null && root.children[i].eow==true){
                char ch=(char) (i+'a');
                temp.append(ch);
                if(temp.length()>ans.length()){
                    ans=temp.toString();
                }   
                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }
    
    public static void main(String[] args) {
       String words[]={"a","banana","app","appl","ap","apply","apple"};

       for(int i=0;i<words.length;i++){
        insert(words[i]);
       }
       longestWord(root, new StringBuilder(""));
       System.out.println(ans);
    }
}
