package Stack;
import java.util.*;

import javax.xml.crypto.Data;
public class PracStackA {
    static class Stack1{
        static ArrayList<Integer>l=new ArrayList<>();
        public static boolean isEmpty(){
            return l.size()==0;
        }
        public static void push(int data){
            l.add(data);
        }
        public static int pop(){
            if(isEmpty()){
                return -1;
            }int top=l.get(l.size()-1);
            l.remove(l.size()-1);
            return top;
        }
        public static int peek(){
            if(isEmpty()){
                return -1;
            }
            return l.get(l.size()-1);
        }
    }
    static class Node{
        int data;
        Node next;
        public  Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    static class Stack2{
        static Node head=null;
        public static boolean isEmpty(){
            return head==null;
        }
        // push
        public void push(int data){
            Node newNode=new Node(data);
            if(isEmpty()){
                head=newNode;
                return;
            }
            newNode.next=head;
            head=newNode;            
        }
        // pop
        public static int pop(){
            if(isEmpty()){
                return -1;
            }
            int top=head.data;
            head=head.next;
            return top;
        }
        // peek
        public static int peek(){
            if(isEmpty()){
                return -1;
            }
            return head.data;
        }
    }
    public static void pushAtBottom(Stack<Integer> s ,int data){
        if(s.empty()){
            s.push(data);
            return;
        }
        int top=s.pop();
        pushAtBottom(s, data);
        s.push(top);
    }
    public static String reveString(String str){
        Stack<Character>s=new Stack<>();
        int idx=0;
        while(idx<str.length()){
            s.push(str.charAt(idx));
            idx++;
        }
        StringBuilder ans=new StringBuilder("");
        while(!s.empty()){
            char curr=s.pop();
            ans.append(curr);
        }
        return ans.toString();
    }
    public static void reverseStack(Stack<Integer>s){
        if(s.empty()){
            return;
        }
        int top=s.pop();
        reverseStack(s);
        pushAtBottom(s, top);
    }
    public  static void printStack(Stack<Integer>s){
        while(!s.empty()){
            System.out.println(s.peek());
            s.pop();
        }
    }

    public static void stockSpan(int stocks[],int span[]){
        Stack <Integer> s=new Stack<>();
        span[0]=1;
        s.push(0);
        for(int i=0;i<stocks.length;i++){
            int currPrice=stocks[i];
            while(!s.isEmpty() && currPrice>stocks[s.peek()]){
                s.pop();
            }if(s.isEmpty()){
                span[i]=i+1;
            }else{
                int prevHigh=s.peek();
                span[i]=i-prevHigh;
            }
            s.push(i);
        }
    }


    public static void main(String[] args) {
        int arr[]={6,8,0,1,3};
        Stack<Integer>s=new Stack<>();
        int nxtGreater[]=new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){
            while(!s.isEmpty()&& arr[s.peek()]<=arr[i]){
                s.pop();
            }
            if(s.isEmpty()){
                nxtGreater[i]=-1;
            }else{
                nxtGreater[i]=arr[s.peek()];
            }
            s.push(i);
        }
        for(int i=0;i<nxtGreater.length;i++){
            System.out.print(nxtGreater[i]+" ");
        }System.out.println();
    }


}
