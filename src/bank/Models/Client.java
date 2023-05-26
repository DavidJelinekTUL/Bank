package bank.Models;

import bank.Enums.Pronounce;
import java.util.ArrayList;


public abstract class Client {
    protected String name;
    protected ArrayList<Account> list;
    protected Pronounce pronounce;
    
    public Client(String name) {
        this.name = name;
        this.list = new ArrayList<>();
        if (name.endsWith("ova")) {
            pronounce = Pronounce.WOMAN;
        }else
            pronounce = Pronounce.MAN;
        
    }

    public String getName() {
        return name;
    }

    public void addAccount(Account acc) {
        list.add(acc);
    }
    
    public String getSalut(Client client){
        switch(client.pronounce){
            case WOMAN:
                return "Paní ";
            case MAN:
                return "Pan ";
            case FIRM:
                return "Firma ";
        }
        return null;
    }
    
    
    public ArrayList<Account> getList(){
        ArrayList<Account> copy = (ArrayList<Account>) list.clone();
        return copy;
    }
    
    public String listAccounts() {
        String AccountsString="";
        for (int i = 0; i < list.size(); i++) {
            AccountsString +=list.get(i).getAccNum()+", ";
        }
        return AccountsString;
    }

    public abstract void displayInfo();
        
}
