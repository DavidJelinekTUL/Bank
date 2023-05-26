/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bank.Comparators;

import bank.Models.Client;
import bank.Ui.Ui;
import java.util.Comparator;


public class CompareByMoney implements Comparator<Client> {
    Ui ui = new Ui();
    
    public int compare(Client c1, Client c2) {
        double money1 = ui.getAllMoney(c1);
        double money2 = ui.getAllMoney(c2);
        return Double.compare(money2, money1);
    }


}
