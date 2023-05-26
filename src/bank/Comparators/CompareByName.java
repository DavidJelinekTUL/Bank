/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bank.Comparators;

import bank.Models.Client;
import bank.Ui.Ui;
import java.util.Comparator;


public class CompareByName implements Comparator<Client> {
Ui ui = new Ui();
    
    public int compare(Client c1, Client c2) {
        String name1 = c1.getName();
        String name2 = c2.getName();
        return name1.compareTo(name2);
    }


}
