package linklist;

import java.nio.file.WatchService;
import java.time.Period;

public class Link{
    class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            next=null;
        }
    }
    static Node head;
    static Node tail;
    static int size;

    public void addFirst(int data){
        Node newNode=new Node(data);
        size++;
        while(head==null){
            head=tail=newNode;
            return;
        }
        newNode.next=head;
        head=newNode;
    }
    public void addLast(int data){
        Node newNode=new Node(data);
        size++;
        while(head==null){
            head=tail=newNode;
            return;
        }
        tail.next=newNode;
        tail=newNode;
    }
    public void print(){
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp=temp.next;
        }System.out.println("null");
    }
    public void addMid(int idx,int data){
        if(idx==0){
            addFirst(data);
            size++;
            return;
        }
        Node newNode=new Node(data);
        size++;
        Node temp=head;
        int i=0;
        while(i<idx-1){
            temp=temp.next;
            i++;
        }
        newNode.next=temp.next;
        temp.next=newNode;
    }
    public int removeFirst(){
        if(size==0){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }if(size==1){
            int val=head.data;
            size=0;
            return val;
        }
        int val=head.data;
        head=head.next;
        size--;
        return val;
    }
    public int removeLast(){
        if(size==0){
            System.out.println("LL is empty");
            return Integer.MIN_VALUE;
        }if(size==1){
            int val= head.data;
            head=tail=null;
            size=0;
            return val;
        }
        Node prev= head;
        for(int i=0;i<size-2;i++){
            prev=prev.next;
        }
        int val=prev.next.data;
        prev.next=null;
        tail=prev;
        size--;
        return val;
    }
    public int intSearch(int key){
        Node temp=head;
        int i=0;
        while(temp!=null){
            if(temp.data==key){
                return i;
            }
            temp=temp.next;
            i++;
        }
        return -1;
    }
    public int helper(Node head,int key){
        if(head==null){
            return -1;
        }
        if(head.data==key){
            return 0;
        }
        int idx=helper(head.next, key);
        if(idx==-1){
            return -1;
        }
        return idx+1;
    }
    public int RescrSearch(Node head,int key){
        return helper(head, key);
    }
    public void reverse(){
        Node prev=null;
        Node curr=tail=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }head=prev;
    }
    public void deleteNthFormEnd(int n){
       int sz=0;
       Node temp=head;
       while(temp!=null){
        temp=temp.next;
        sz++;
       }
       if(n==sz){
        head=head.next;
        return;
       }
       int i=1;
       int iTofind=sz-n;
       Node prev=head;
       while(i<iTofind){
        prev=prev.next;
        i++;
       }
       prev.next=prev.next.next;
       return;
    }
    public Node Findmid(Node head){
        Node slow=head;
        Node fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }return slow;
    }
    public boolean isPalindrome(){
        if(head==null && head.next==null){
            return true;
        }
        Node midNode=Findmid(head);
        Node prev=null;
        Node curr=midNode;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node right=prev;
        Node Left=head;
        while(right!=null){
            if(Left.data!=right.data){
                return false;
            }
            Left=Left.next;
            right=right.next;
        }
        return true;
    }
    public boolean isCycle(){
        Node slow =head;
        Node fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }return false;
    }
    public static void removeCycle(){
        Node slow=head;
        Node fast=head;
        Boolean cycle=false;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                cycle= true;
                break;
            }
        }
        if(cycle==false){
            return;
        }
        slow=head;
        Node prev=null;
        while(slow!=fast){
            prev=fast;
            slow=slow.next;
            fast=fast.next;
        }
        prev.next=null;
    }
    public static Node swap(Node head,int idx1,int idx2){
        Node temp1=head;
        Node temp2=head;
        Node prev1=null;
        Node prev2=null;
        Node temp=head;
        int count1=0, count2=0;
        while(count1!=idx1){
            prev1=temp1;
            temp1=temp1.next;
            count1++;   
        }
        while(count2!=idx2){
            prev2=temp2;
            temp2=temp2.next;
            count2++;
        }
        if(idx1==0){
            Node help=temp1.next;
            temp1.next=temp2.next;
            temp2.next=help;
            prev2.next=temp1;
            return temp2;
        }else{
        prev1.next=temp2;
        Node help=temp1.next;
        prev2.next=temp1;
        temp1.next=temp2.next;
        temp2.next=help;
        return head;
        }
    }



    public static void main(String[] args) {
        Link ll=new Link();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(1);
        ll.print();
        System.out.println(ll.isPalindrome());
        

        // System.out.println(ll.RescrSearch(head,6));
    }


 }