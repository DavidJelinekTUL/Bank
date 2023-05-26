package bank.Models;

import java.util.ArrayList;

public class ClientDatabase {
    ArrayList<Client> db = new ArrayList<>();
    
    public void addClient(Person client){
        db.add(client);
    }
    public void addClient(Company client){
        db.add(client);
    }
    
    public ArrayList<Client> getAll(){
        ArrayList<Client> dbClone = (ArrayList<Client>) db.clone();
        return dbClone;
    }
    
    public Person getClient(String name){
       ArrayList<Person> dbClone = (ArrayList<Person>) db.clone();
        for (int i = 0; i < dbClone.size(); i++) {
            if (db.get(i).getName().equals(name)) {
                return (Person)db.get(i);
            }
        }
        return null;
    }
    
    public Company getClientC(String name){
       ArrayList<Person> dbClone = (ArrayList<Person>) db.clone();
        for (int i = 0; i < dbClone.size(); i++) {
            if (db.get(i).getName().equals(name)) {
                return (Company)db.get(i);
            }
        }
        return null;
    }
    
    public boolean exists(String name){
       ArrayList<Person> dbClone = (ArrayList<Person>) db.clone();
        for (int i = 0; i < dbClone.size(); i++) {
            if (db.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
}
