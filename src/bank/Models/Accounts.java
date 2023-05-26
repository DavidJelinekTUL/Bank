package bank.Models;

import java.util.ArrayList;

public class Accounts {

    Account acc;
    public ArrayList<Account> db = new ArrayList<>();
    private int id = 0;

    public int createAccount(Double balance) {
        acc = new Account(id, balance);
        db.add(acc);
        autoIncrement();
        return id;
    }

    public ArrayList<Account> outList() {
        ArrayList<Account> list = (ArrayList<Account>) db.clone();
        return list;
    }

    private void autoIncrement() {
        id++;
    }

    public boolean insertMoney(Double money, Account acc) {
        if (money > 0) {
            acc.addMoney(money);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean withdrawMoney(Double money, Account acc) {
        if (money < acc.getBalance()) {
            acc.withdrawMoney(money);
            return true;
        } else {
            return false;
        }
    }

}
