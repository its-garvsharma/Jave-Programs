package linklist;

public class LinkedList {
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){
        // step 1=create new node
        Node newNode=new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        // step 2 newNode next = head
        newNode.next=head; //link


        // step3-head=newHead
        head=newNode;
    }
    public void addLast(int data){
        Node newNode=new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        tail.next=newNode;
        tail=newNode;
        
    }
    public void print(){ //O(n) 
        if(head==null){
            System.out.println("Linkedlist is empty");
            return;
        }
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"-> ");
            temp=temp.next;
        }
        System.out.println("null");
    }
    public void add(int idx,int data){
        if(idx==0){
            addFirst(data);
            return;
        }
        Node newNode=new Node(data);
        size++;
        Node temp=head;
        int i=0;

        while(i < idx-1){
            temp=temp.next;
            i++;
        }
        // i=idx-1; temp-> prev
        newNode.next=temp.next;
        temp.next=newNode;
    }
    public int removeFirst(){
        if(size==0){
            System.out.println("LinkedList is empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val=head.data;
            size=0;
            head=tail=null;
            return val;
        }
        int val=head.data;
        head=head.next;
        size--;
        return val;
    }
    public int removeLast(){
        if(size==0){
            System.out.println("ll is empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        // prev : i=size-2
        Node prev=head;
        for(int i=0;i<size-2;i++){
            prev=prev.next;
        }
        int val=prev.next.data;
        prev.next=null;
        tail=prev;
        size--;
        return val;
    }
    public int itraSearch(int key){ //O(n)
        Node temp=head;
        int i=0;
        while(temp!=null){
            if(temp.data==key){
                return i;
            }
            temp=temp.next;
            i++;
        }
        // key not found
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
        return idx +1;
    }
    public int RescrSearch(int key){
        return helper(head, key);
    }

    public void reverse(){
        Node prev=null;
        Node curr = tail=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head=prev;
    }

    public void deleteNthfromEnd(int n){
        // calculate size
        int sz=0;
        Node temp=head;
        while(temp!=null){
            temp=temp.next;
            sz++;
        }
        if(n==sz){
            head=head.next; //remove First
            return;
        }
        // sz-n
        int i=1;
        int iToFind=sz-n;
        Node prev=head;
        while(i<iToFind){
            prev=prev.next;
            i++;
        }
        prev.next=prev.next.next;
        return ;
    }
    public Node findMid(Node head){
        Node slow=head;
        Node fast=head;
        while(fast !=null && fast.next !=null){
            slow=slow.next; //+1
            fast=fast.next.next; //+2   
        }
        return slow ; //slow is my midle
    }
    public boolean isPalindrome(){
        if(head==null || head.next==null){
            return true;
        }
        // step1 - find mid
        Node midNode=findMid(head);
        // step 2- reverse 2nd half
        Node prev=null;
        Node curr=midNode;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node right = prev; //right half head
        Node left =head;

        // step 3 check left half &right half
        while(right!=null){
            if(left.data!=right.data){
                return false;
            }
            left=left.next;
            right=right.next;
        }
        return true;
    }
    // public String llToString(){
        
    // }

    //  This code is needed to be checked !!
    public boolean checkPalindrome(Node head){
        String str="";
        Node temp=head;
        while(temp !=null){
            str+=temp;
            temp=temp.next;
        }
        System.out.println(str);

        String ori=str;
        String ans="";
        for(int i=str.length();i>0;i--){
            ans+=str.charAt(i-1);
        }
        if(ori==ans){
            return true;
        }
        return false;
    }
    public static boolean isCylce(){
        Node slow=head;
        Node fast=head;
        while(fast !=null && fast.next!=null){
            slow=slow.next; //+1
            fast=fast.next.next; //+2
            if(slow==fast){
                return true; //Cylce found
            }
        }
        return false; //cylce not foud;
    }
    public static void removeCylce(){
        // Detct code
        Node slow=head;
        Node fast=head;
        boolean cylce=false;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;

            if(slow==fast){
                cylce=true;
                break;
            }
        }
        if(cylce==false){
            return;
        }
        // find meeting point 
        slow=head;
        Node prev=null; //last node
        while(slow !=fast){
            prev=fast;
            slow=slow.next;
            fast=fast.next;
        }
        // remove cycle -> last.next =null
        prev.next=null;

    }
    private Node getMid(Node head){
        Node slow=head;
        Node fast=head.next;
        while( fast !=null  && fast.next !=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow; //mid node
    }
    private Node merge(Node head1, Node head2){
        Node mergedLL= new Node(-1);
        Node temp=mergedLL;

        while(head1 != null && head2 !=null){
            if(head1.data <=head2.data){
                temp.next   =head1;
                head1=head1.next;
                temp=temp.next;
            }else{
                temp.next=head2;
                head2=head2.next;
                temp=temp.next;
                
            }
        }
        while(head1 !=null){
                temp.next=head1;
                head1=head1.next;
                temp=temp.next;
        }
        
        while(head2 !=null){
                temp.next=head2;
                head2=head2.next;
                temp=temp.next;
        }
        return mergedLL.next;
        
    }
    public Node mergeSort(Node head){
        if(head==null || head.next == null){
            return head;
        }
        // finf mid
        Node mid=getMid(head);
        // left & right MS
        Node rightHead=mid.next;
        mid.next=null;
        Node newLeft = mergeSort(head);
        Node newRight = mergeSort(rightHead);
        mergeSort(rightHead);

        // merge
        return merge(newLeft,newRight);
    }
    public boolean checkpali(Node head){
        int temp[]=new int[size];
        Node x=head;
        for(int i=0;i<size;i++){
            temp[i] = x.data;
            x=x.next;
        }
        // for(int i=0;i<temp.length;i++){
        //     System.out.print(temp[i]+" ");
        // }
        int y[]=temp;
        int z[]=new int[temp.length];
        int j=0;
        for(int i=temp.length;i>0;i--){
            z[j]=temp[i-1];
            j++;
        }
        for(int i=0;i<temp.length;i++){
            System.out.print(y[i]+" ");
        }
        for(int i=0;i<y.length;i++){
            if(y[i]!=z[i]){
                return false;
            }
        }
        return true;
    }

    public void zigZag(){
        // find mid
        Node slow=head;
        Node fast=head.next;
        while(fast !=null && fast.next !=null){
            slow =slow.next;
            fast=fast.next.next;
        }
        Node mid=slow;

        // find reverse 2nd half
        Node curr=mid.next;
        mid.next=null;
        Node prev =null;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node left=head;
        Node right =prev;
        Node nextL,nextR;
        // alt merge -zig zag
        while(left!=null && right !=null ){
            nextL =left.next;
            left.next=right;
            nextR=right.next;
            right.next=nextL;

            left=nextL;
            right=nextR;
        }
    }
    public Node getIntersectionNode(Node head1,Node head2){
        while(head2!=null){
            Node temp=head1;
            while(temp!=null){
                if(temp==head2){
                    return head2;
                }temp=temp.next;

            }
            head2=head2.next;
        }
        return null;
    }
    static Node push(Node head_ref,int new_data){
        Node new_node=new Node(new_data);
        new_node.next=head_ref;
        head_ref=new_node;
        return head_ref;
    }
    public void skipMdeletN(Node head,int m,int n){
        Node temp=head;
        while(temp!=null){
            int count=0,del=0;
            while(count!=m-1 && temp!=null){
                temp=temp.next;
                count++;
            }
            
            Node curr=temp; 
            
            while(del!=n+1 && temp!=null){
                temp=temp.next;
                del++;
            }
            if(del!=n+1){
                curr.next=null;
            }else{
                curr.next=temp;
            }
        }
    }
    public void print2(Node head){ //O(n) 
        if(head==null){
            System.out.println("Linkedlist is empty");
            return;
        }
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"-> ");
            temp=temp.next;
        }
        System.out.println("null");
    }
    
    public static void swap(Node head,int idx1,int idx2){
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
            Node help=temp2.next;
            temp2.next=temp1.next;
            prev2.next=temp1;
            temp1.next=help;
            head=temp2;
            return;
        }else{
        prev1.next=temp2;
        Node help=temp1.next;
        prev2.next=temp1;
        temp1.next=temp2.next;
        temp2.next=help;
        }
    }
    public void swapNode(int x,int y){
        if(x==y){
            return;
        }
        Node prevX=null,currX=head;
        while(currX!=null && currX.data!=x){
            prevX=currX;
            currX=currX.next;
        }
        Node prevY=null ,currY=head;
        while(currY!=null&&currY.data!=y){
            prevY=currY;
            currY=currY.next;
        }
        if(currX==null ||currY==null){
            return;
        }
        if(prevX!=null){
            prevX.next=currY;
        }else{
            head=currY;
        }if(prevY!=null){
            prevY.next=currX;
        }else{
            head=currX;
        }

        Node temp=currX.next;
        currX.next=currY.next;
        currY.next=temp;

    }
    public static Node SortedMerge(Node a, Node b){
        Node result=null;
        if(a==null){
            return b;
        }else if(b==null){
            return a;
        }
        if(a.data<=b.data){
            result=a;
            result.next=SortedMerge(a.next, b);
        }else{
            result=b;
            result.next=SortedMerge(a, b.next);
        }
        return result;
    }
    public static Node mergeKLists(Node arr[],int last){
        while(last!=0){
            int i=0,j=last;
            while(i<j){
                arr[j]=SortedMerge(arr[i], arr[j]);
                i++;
                j--;
                if(i>=j){
                    last=j;
                }
            }
        }
        return arr[0];
    }
    public static void printList(Node node){
        while(node!=null){
            System.out.print(node.data+" ");
            node=node.next;
        }
    }


    public static void main(String[] args) {
        // int k=3,n=4;
        // Node arr[]=new Node[k];

        // arr[0]=new Node(1);
        // arr[0].next=new Node(3);
        // arr[0].next.next=new Node(5);
        // arr[0].next.next.next=new Node(7);
        
        // arr[1]=new Node(2);
        // arr[1].next=new Node(4);
        // arr[1].next.next=new Node(6);
        // arr[1].next.next.next=new Node(8);

        // arr[2]=new Node(0);
        // arr[2].next=new Node(9);
        // arr[2].next.next=new Node(10);
        // arr[2].next.next.next=new Node(11);

        // Node head=mergeKLists(arr, k-1);
        // printList(head);
        LinkedList ll=new LinkedList();
        ll.addFirst(28);
        ll.addLast(6);
        ll.addLast(3);
        ll.addLast(2);
        ll.addLast(9);
        // ll.addMid(6, 3);
        ll.print();
        
        ll.mergeSort(head);
        ll.print();

     }
}
