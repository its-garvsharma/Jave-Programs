public class OPPS {
    public static void main(String[] args) {
        Calcultor cal=new Calcultor();
        System.out.println(cal.sum(3.4f, 4.5f));
        System.out.println(cal.sum(3, 4));
    }
}
class Garv{
    String Name;
    int Age;
    String Address;
    void setName(String newName){
        Name=newName;
    }  
}
class Pen{
    String color;
    int Tip;
    void setColor(String newColor){
        color=newColor;
    }
    void setTip(int newTip){
        Tip=newTip;
    }
    String getColor(){
        return this.color;
    }
    int getTip(){
        return this.Tip;
    }
}
class BankAccount{
    private String Password;
    protected int Account_num;
    public String Name;
    public void setPassord(String newPass){
        Password=newPass;
    }
    protected void setAccount(int newAcc){
        Account_num=newAcc;
    }
}
// Encapsulation -Wrapping of data or methods in a single unit(or entities).
// also implement data hiding.
 
// constructors_-It initalize the objects.
class Student{
    String Name;
    int roll;
    
    Student(String Name){
        this.Name=Name;
        
    }
    Student(int roll){
        this.roll=roll;
    }
    Student(){
        System.out.println("Constructor is called");
    }
}
// Inheritance -when properties and methods of base class are passed on to derived class.
// base Case
class Animal{
    String color;
    void eat(){
        System.out.println("Eats");
    }
    void breath(){
        System.out.println("breaths");
    }
}
class Fish extends Animal{
    int Fins;
    void swims(){
        System.out.println("Swim in water!!");
    }
}
class Mammals extends Animal{
    int legs;
    void walks(){
        System.out.println("walks");
    }
}
class Dog extends Mammals{
    String breed;
}
class Bird extends Animal{
    void fly(){
        System.out.println("You can fly");
    }
}

class Calcultor{
    int sum(int a,int b){
        return a+b;
    }
    float sum(float a,float b){
        return a+b;
    }
    int sum(int a,int b,int c){
        return a+b+c;
    }
}


