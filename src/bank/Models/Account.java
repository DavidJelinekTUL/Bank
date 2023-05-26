package bank.Models;

public class Account {
    private int accNum;
    private double balance;

    public Account(int accNum, double balance) {
        this.accNum = accNum;
        this.balance = balance;
    }

    public int getAccNum() {
        return accNum;
    }

    public double getBalance() {
        return balance;
    }
       
    public void addMoney(Double insertMoney){
        this.balance +=insertMoney;
    }
    public void withdrawMoney(Double insertMoney){
        this.balance -=insertMoney;
    }
}
