import java.util.*;
public class Threading {
    public static void main(String[] args) {
        // MythreasRunnable1 bullet1=new MythreasRunnable1();
        // MythreasRunnable2 bullet2=new MythreasRunnable2();
        // Thread gun1=new Thread(bullet1);
        // Thread gun2=new Thread(bullet2);
        // // gun1.start();
        // // gun2.start();
        // MyThr t=new MyThr("Garv");
        // MyThr g=new MyThr(bullet1,"garv");
        // t.start();
        // g.start();

        // System.out.println("The id of this id is : "+g.getName());
        // BankActivity bob=new BankActivity("Bob", 1000);
        // BankActivity roy=new BankActivity("Roy", 1000);
        // bob.start();
        // roy.start();
        // try {
        //     bob.join();
        //     roy.join();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // int finalbal=BankAcccount.getBalance();
        // System.out.println("final balance: "+finalbal);
        // Crypto crypto = new Crypto();
        // Arun arun = new Arun(crypto);
        // Vijay vijay = new Vijay(crypto);

        // arun.start();
        // vijay.start();



    }
}
class Mythreas extends Thread{
    @Override
    public void run(){
        int i=0;
        while(i<400){
            System.out.println("MY Thread is running");
            System.out.println("I am happy!!");
            i++;


        }
    }
}
class Mythreas2 extends Thread{
    @Override
    public void run(){
        int i=0;
        while(i<400){
            System.out.println(" Thread2 is good");
            System.out.println("I am Sad!!");
            i++;

        }
    }
}
class MythreasRunnable1 implements Runnable{
    int i=1;
    public void run(){
        System.out.println("I am a thread1 not a threat");
    }
}
class MythreasRunnable2 implements Runnable{
    public void run(){
        System.out.println("I am a thread2 not a threat");
    }
}
class MyThr extends Thread{
    public MyThr(Runnable r,String name){
        super(r, name);
    }
    public MyThr(String name){
        super(name);
    }
    public void run(){
        int i=0;
        while(i<20){
            System.out.println("I am a Thread");i++;
        }
    }
}
class BankAcccount{
    public static int bal=100000;
    public static synchronized void withdraw(String name,int amount){
        if(amount<=0){
            System.out.println("Invalid ammount");
            return;
        } if(amount>bal){
            System.out.println("Insuffient balance");
        }if(bal<0){
            System.out.println("Account is overwithdrwan");
        }
        System.out.println(name+" amount withdrwa: "+amount);
        bal-=amount;
        System.out.println("Remaining balnce: "+bal);
    }
    public static int getBalance(){
        return bal;
    }
    
}
class BankActivity extends Thread {
    private String accountHolder;
    private int withdrawalAmount;

    public BankActivity(String accountHolder, int withdrawalAmount) {
        this.accountHolder = accountHolder;
        this.withdrawalAmount = withdrawalAmount;
    }

    @Override
    public void run() {
        BankAcccount.withdraw(accountHolder, withdrawalAmount);
    }
}
class Crypto {
    private String message;

    public synchronized void plain(String name, String mess) {
        this.message = mess;
        System.out.println(name + ": " + mess);
        notify();
        try {
            wait(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void encrypt(String name) {
        try {
            wait(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String encryptedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            char ch = (char) (message.charAt(i) + 3);
            encryptedMessage += ch;
        }
        System.out.println(name + ": " + encryptedMessage);
        notify(); 
    }
}

class Arun extends Thread {
    private Crypto crypto;

    public Arun(Crypto crypto) {
        this.crypto = crypto;
    }

    @Override
    public void run() {
        crypto.plain("Arun", "abe");
        crypto.encrypt("Arun");
        crypto.plain("Arun", "Hello");
        crypto.encrypt("Arun");
        crypto.plain("Arun", "welcome");
        crypto.encrypt("Arun");
    }
}

class Vijay extends Thread {
    private Crypto crypto;

    public Vijay(Crypto crypto) {
        this.crypto = crypto;
    }

    @Override
    public void run() {
        try {
            sleep(20); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        crypto.encrypt("Vijay");
        crypto.plain("Vijay", "def");
        crypto.encrypt("Vijay");
    }
}
class Task extends Thread{
    int task;
    public Task(int task){
        this.task=task;
    }
    public void run(){
        try {
            System.out.println("Task "+task+" Started");
            Thread.sleep(2000);
            System.out.println("Task "+task+" Completed");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Cinema{
    
} 

