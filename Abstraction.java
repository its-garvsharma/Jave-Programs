import javax.sound.midi.VoiceStatus;

public class Abstraction {
    public static void main(String[] args) {
        Student s1=new Student();
        s1.schoolName="JMV";
        Student s2=new Student();
        System.out.println(s2.schoolName);
        Student s3=new Student();
        s3.schoolName="DPS";
        System.out.println(s3.schoolName);
        System.out.println(s1.schoolName);
        System.out.println(s2.schoolName);

    }
}
abstract class Animal{
    static int returnPercentage(int math,int phy,int chem){
        return(math+phy+chem)/3;
    }
    String color;
    Animal(){
        color="brown";
    }
    void eat(){
        System.out.println("animal eats");
    }
    abstract void walks();
}
class Horse extends Animal{
    void changeColor(){
        color="dark brown";
    }
    void walks(){
        System.out.println("walks on 4 legs");
    }
}
class Chicken extends Animal{
    void changeColor(){
        color="yellow";
    }
    void walks(){
        System.out.println("walks on 4 legs !!");
    }
}

interface ChessPlayer{
    void moves();
}
class Queen implements ChessPlayer{
    public void moves(){
        System.out.println("up,down,left,right,diagonal");
    }
}
class Rook implements ChessPlayer{
    public void moves(){
        System.out.println("up,down,right,left");
    }
}
interface Harbivours{
    void veg();
}
interface Carnivours{
    void nonveg();
}
class bear implements Harbivours,Carnivours{
    public void veg(){
        System.out.println("the vegetarian!!");
    }
    public void nonveg(){
        System.out.println("the non vegetarian!!");
    }
}

class Student{
    String name;
    int roll;
    static String schoolName;
    void setName(String name){
        this.name=name;
    }
    String getName(){
        return this.name;
    }
}
