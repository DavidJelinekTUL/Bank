/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Utils;

import bank.Models.Account;
import bank.Models.Accounts;
import bank.Models.Company;
import bank.Models.Person;
import java.util.ArrayList;

public class Utils {
    Accounts db = new Accounts();
    ArrayList<Account> list = db.outList();

    public Account createAcc(Double input) {
        int id = db.createAccount(input);
        list = db.outList();
        id--;
        return list.get(id);
    }
    
    public boolean addMoney(int accNum, Double money){
        Account acc = list.get(accNum);
        return db.insertMoney(money, acc);
    }
    
    public boolean withdrawMoney(int accNum, Double money){
        Account acc = list.get(accNum);
        return db.withdrawMoney(money, acc);
    }
    
    public Account getAccInfo(int accNum){
        return list.get(accNum);
    }
    
    public boolean isDouble(String input){
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean isInt(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean isHis(Person client, int accNum){
        for (int i = 0; i < client.getList().size(); i++) {
            if (client.getList().get(i).getAccNum()==(accNum)) {
                return true;
            }
        }
        return false;
    }  
    public boolean isHis(Company client, int accNum){
        for (int i = 0; i < client.getList().size(); i++) {
            if (client.getList().get(i).getAccNum()==(accNum)) {
                return true;
            }
        }
        return false;
    }  
    
}
