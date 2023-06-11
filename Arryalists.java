import java.net.http.HttpResponse.PushPromiseHandler;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
public class Arryalists {
    public static void main(String[] args) {
        ArrayList<Integer>list=new ArrayList<>();
        // 11,15,6,8,9,10
        list.add(10);
        list.add(6);
        list.add(5);
        list.add(8);
        System.out.println(findlonely(list));
    }
    public static void swap(ArrayList<Integer>list,int idx,int idx2){
        int temp=list.get(idx);
        list.set(idx, list.get(idx2));
        list.set(idx2, temp);
    }
    public static int storeWater(ArrayList<Integer>height){
        int maxWater=0;
        for(int i=0;i<height.size();i++){
            for(int j=i+1;j<height.size();j++){
                int ht=Math.min(height.get(i),height.get(j));
                int width=j-i;
                int currWater= ht* width;
                maxWater=Math.max(maxWater,currWater);
            }
        }
        return maxWater;
    }
    public static int storeWater2(ArrayList<Integer>height){
        int maxWater=0;
        int lp=0; 
        int rp=height.size()-1;
        while(lp<rp){
            // calculate water area
            int ht=Math.min(height.get(lp),height.get(rp));
            int width=rp-lp;
            int currWater=ht* width;
            maxWater=Math.max(maxWater,currWater);
            // update ptr
            if(height.get(lp)<height.get(rp)){
                lp++;
            }else{
                rp--;
            }

        }
        return maxWater;
    }
    public static boolean pairsum(ArrayList<Integer>list,int tar){
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.size();j++){
                if((list.get(i)+list.get(j))==tar){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean pairsum1(ArrayList<Integer>list,int tar){
        int lp=0;
        int rp=list.size()-1;
        while(lp!=rp){
            // case1
            if(list.get(lp)+list.get(rp)==tar){
                return true;
            }
            if(list.get(lp)+list.get(rp)<tar){
                lp++;
            }
            if(list.get(lp)+list.get(rp)>tar){
                rp--;
            }
        }
        return false;
    }
    public static boolean pairsum2(ArrayList<Integer>list,int target){
        int bp=-1;
        int n=list.size();
        for(int i=0;i<list.size();i++){
            if(list.get(i)>list.get(i+1)){
                bp=i;
                break;
            }
        }
        int lp=bp+1; //smallest
        int rp=bp; //largest 

        while(lp!=rp){
            // case1
            if(list.get(lp)+list.get(rp)==target){
                return true;
            }
            // case 2
            if(list.get(rp)+list.get(lp)<target){
                lp=(lp+1)%n;
            }else{
                rp=(n+rp-1)%n;
            }
        }
        return false;
    }
    public static boolean monotonic(ArrayList<Integer>list){
        boolean inc=true;
        boolean dec=true;
        for(int i=0;i<list.size()-1;i++){
            if(list.get(i)>list.get(i+1)){
                inc=false;
            }if(list.get(i)<list.get(i+1)){
                dec=false;
            } 
        }
        return inc ||dec;
    }
    public static void lonely_number(ArrayList<Integer>list){
        for(int i=1;i<list.size()-1;i++){
            int prev=list.get(i)-1;
            int upcome=list.get(i)+1;
            if(list.get(i-1)==prev && list.get(i+1)==upcome){
                System.out.println(list.get(i));
            }
        }
    }
    public static ArrayList<Integer> findlonely(ArrayList<Integer>list){
        Collections.sort(list);
        ArrayList<Integer>list2=new ArrayList<>();
        for(int i=1;i<list.size()-1;i++){
            if(list.get(i-1)+1<list.get(i) && list.get(i)+1<list.get(i+1)){
                list2.add(list.get(i));
            }
        }
        if(list.size()==1){
            list2.add(list.get(0));
        }
        if(list.size()>1){
            if(list.get(0)+1<list.get(1)){
                list2.add(list.get(0));
            }
            if(list.get(list.size()-2)+1<list.get(list.size()-1)){
                list2.add(list.get(list.size()-1));
            }
        }
        return list2;
    }
}  