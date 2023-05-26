/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.Models;

public class Person extends Client {

    public Person(String name) {
        super(name);
    }

    @Override
    public void displayInfo() {
        System.out.println("Person: " + name);
        System.out.println("Gender of the client: "+pronounce);
        System.out.println("Accounts:");
        for (Account account : list) {
            System.out.println("Account Number: " + account.getAccNum());
            System.out.println("Balance: " + account.getBalance());
        }
    }

}
