package bank.Ui;

import bank.Comparators.CompareByMoney;
import bank.Comparators.CompareByName;
import bank.Models.Account;
import bank.Models.Client;
import bank.Models.ClientDatabase;
import bank.Models.Company;
import bank.Models.Person;
import bank.Utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ui {

    Utils u = new Utils();
    ClientDatabase db = new ClientDatabase();
    Scanner sc = new Scanner(System.in);

    public void startApp() {

        String choice;

        do {
            displayMenu();
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    createClient();
                    break;
                case "2":

                    System.out.println("Přejete si přihlásit se jako Fyzická osoba a nebo jako firma?");
                    System.out.println("1. Fyzická osoba");
                    System.out.println("2. Firma");
                    System.out.println("3. zpět");

                    choice = sc.nextLine();

                    switch (choice) {
                        case "1":
                            Person client;
                            try {
                                client = loginClient();
                            } catch (ClassCastException e) {
                                break;
                            }
                            if (client == null) {
                                break;
                            }
                            String choice2;
                            do {
                                displayMenuUsr(client.getSalut(client) + client.getName());
                                choice2 = sc.nextLine();
                                switch (choice2) {
                                    case "1":
                                        System.out.println("Vybrána možnost: Vytvořit účet");
                                        client.addAccount(createAcc());
                                        break;
                                    case "2":
                                        insertMoney(client);
                                        break;
                                    case "3":
                                        withdrawMoney(client);
                                        break;
                                    case "4":
                                        accInfo(client);
                                        break;
                                    case "5":
                                        allAccInfo(client);
                                        break;
                                    default:
                                        System.out.println("Neplatná volba. Zadejte platnou možnost.");
                                }
                            } while (!choice2.equals("6"));
                            break;
                        case "2":
                            Company company;
                            try {
                                company = loginClientC();
                            } catch (ClassCastException e) {
                                break;
                            }
                            if (company == null) {
                                break;
                            }
                            do {
                                displayMenuUsr(company.getSalut(company) + company.getName());
                                choice2 = sc.nextLine();
                                switch (choice2) {
                                    case "1":
                                        System.out.println("Vybrána možnost: Vytvořit účet");
                                        company.addAccount(createAcc());
                                        break;
                                    case "2":
                                        insertMoney(company);
                                        break;
                                    case "3":
                                        withdrawMoney(company);
                                        break;
                                    case "4":
                                        accInfo(company);
                                        break;
                                    case "5":
                                        allAccInfo(company);
                                        break;
                                    default:
                                        System.out.println("Neplatná volba. Zadejte platnou možnost.");
                                }
                            } while (!choice2.equals("6"));
                            break;
                        default:
                            System.out.println("Neplatná volba");
                            break;
                    }
                    break;

                case "3":
                    displayMenuAdmin();
                    choice = sc.nextLine();
                    switch (choice) {
                        case "1":
                            outputAllAdmin(0);
                            break;
                        case "2":
                            outputAllAdmin(1);
                            break;
                        case "3":
                            outputAllAdmin(2);
                            break;
                        default:
                            System.out.println("Neplatná volba.");
                            break;
                    }
                    break;
                case "quit":
                    System.out.println("Ukončuji program.");
                    break;
                default:
                    System.out.println("Neplatná volba. Zadejte prosím platnou volbu.");
                    break;
            }

        } while (!choice.equals(
                "quit"));

    }

    private void outputAllAdmin(int comp) {
        ArrayList<Client> dbClone = (ArrayList<Client>) db.getAll().clone();
        if (comp==1) {
            System.out.println("Vypisuji veškeré informace z databáze (setříděné podle celkového stavu účtu): ");
            Collections.sort(dbClone, new CompareByMoney());
        }else if(comp==2){
            System.out.println("Vypisuji veškeré informace z databáze (setříděné podle jména klientů): ");
            Collections.sort(dbClone, new CompareByName());
        }
        for (int i = 0; i < dbClone.size(); i++) {
            if (dbClone.get(i) instanceof Company) {
                System.out.println("Klient " + dbClone.get(i).getSalut(dbClone.get(i)) + dbClone.get(i).getName() + " má na všech účtech " + getAllMoney(dbClone.get(i)) + "kč. ");
            } else if (dbClone.get(i) instanceof Person) {
                System.out.println("Klient " + dbClone.get(i).getSalut(dbClone.get(i)) + dbClone.get(i).getName() + " má na všech účtech " + getAllMoney(dbClone.get(i)) + "kč. ");

            }
        }
    }    

    public Double getAllMoney(Client client) {
        Double balance = 0.0;
        for (int i = 0; i < client.getList().size(); i++) {
            balance += client.getList().get(i).getBalance();
        }
        return balance;
    }

    public Person loginClient() {
        Person client = null;
        while (true) {
            System.out.println("Zadejte jméno (nebo \"back\" pro návrat): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("back")) {
                break;
            }

            client = db.getClient(input);

            if (client == null) {
                System.out.println("Uživatel nenalezen.");
            } else {
                System.out.println("Úspěšně přihlášen za " + client.getName());
                break;
            }
        }

        return client;
    }

    public Company loginClientC() {
        Company client = null;
        while (true) {
            System.out.println("Zadejte jméno (nebo \"back\" pro návrat): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("back")) {
                break;
            }

            client = db.getClientC(input);

            if (client == null) {
                System.out.println("Uživatel nenalezen.");
            } else {
                System.out.println("Úspěšně přihlášen za " + client.getName());
                break;
            }
        }

        return client;
    }

    public void createClient() {
        System.out.println("Přejete si vytvořit klienta pro firmu nebo fyzickou osobu?");
        System.out.println("1. Fyzická osoba");
        System.out.println("2. Firma");
        System.out.println("3. zpět");

        String choice = sc.nextLine();
        String input;
        Account acc;
        switch (choice) {
            case "1":
                System.out.println("Zadejte jméno ");
                input = sc.nextLine();
                if (db.exists(input)) {
                    System.out.println("Klient s tímto jménem už existuje. Vyberte si jiné");
                    break;
                }
                Person client = new Person(input);
                acc = createAcc();
                client.addAccount(acc);
                db.addClient(client);
                break;
            case "2":
                System.out.println("Zadejte jméno ");
                input = sc.nextLine();
                if (db.exists(input)) {
                    System.out.println("Klient s tímto jménem už existuje. Vyberte si jiné");
                    break;
                }
                Company company = new Company(input);
                acc = createAcc();
                company.addAccount(acc);
                db.addClient(company);
                break;
            case "3":
                break;
            default:
                System.out.println("Neplatná volba.");
                break;
        }

    }

    public void accInfo(Person client) {
        System.out.println("Vybrána možnost: Vypsat info o účtu");
        System.out.println("Výpis všech účtů vlastněných klientem " + client.getName() + ".");
        System.out.println(client.listAccounts());

        System.out.println("Napište číslo účtu: ");
        String accNumin = sc.nextLine();
        while (!u.isDouble(accNumin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            accNumin = sc.nextLine();
        }

        int accNum = Integer.parseInt(accNumin);
        if (u.isHis(client, accNum)) {
            System.out.println("Na účtu " + u.getAccInfo(accNum).getAccNum() + " je " + u.getAccInfo(accNum).getBalance() + "kč. ");
        } else {
            System.out.println("Tento účet nepatří klientu " + client.getName());
        }

    }

    public void accInfo(Company client) {
        System.out.println("Vybrána možnost: Vypsat info o účtu");
        System.out.println("Výpis všech účtů vlastněných klientem " + client.getName() + ".");
        System.out.println(client.listAccounts());

        System.out.println("Napište číslo účtu: ");
        String accNumin = sc.nextLine();
        while (!u.isDouble(accNumin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            accNumin = sc.nextLine();
        }

        int accNum = Integer.parseInt(accNumin);
        if (u.isHis(client, accNum)) {
            System.out.println("Na účtu " + u.getAccInfo(accNum).getAccNum() + " je " + u.getAccInfo(accNum).getBalance() + "kč. ");
        } else {
            System.out.println("Tento účet nepatří klientu " + client.getName());
        }

    }

    public void clientInfo() {
        System.out.println("Napiš jméno účtu.");
        String name = sc.nextLine();
        Person client = db.getClient(name);
        client.displayInfo();
    }

    public Account createAcc() {
        System.out.println("Zadej vstupní sumu pro nový účet");
        String balancein = sc.nextLine();
        while (!u.isDouble(balancein)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            balancein = sc.nextLine();
        }

        Double balance = Double.parseDouble(balancein);

        Account acc = u.createAcc(balance);
        displaySpace();
        System.out.println("vytvářím účet s číslem " + acc.getAccNum());
        System.out.println("Účet s číslem " + acc.getAccNum() + " byl vytvořen. Jeho zůstatek je: " + acc.getBalance() + " kč.");
        return acc;
    }

    public void insertMoney(Person client) {
        System.out.println("Vybrána možnost: Přidat peníze na účet");
        System.out.println("Výpis všech účtů vlastněných klientem " + client.getName() + ".");
        System.out.println(client.listAccounts());
        System.out.println("Napište číslo účtu: ");
        String accNumin = sc.nextLine();
        while (!u.isDouble(accNumin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            accNumin = sc.nextLine();
        }
        System.out.println("Napište kolik chcete přidat: ");
        String moneyin = sc.nextLine();
        while (!u.isDouble(moneyin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            moneyin = sc.nextLine();
        }

        int accNum = Integer.parseInt(accNumin);
        Double money = Double.parseDouble(moneyin);

        if (u.isHis(client, accNum)) {
            if (u.addMoney(accNum, money)) {
                System.out.println("Na účet " + accNum + " bylo připsáno " + money + " kč.");
            } else {
                System.out.println("Něco se nepovedlo.");
            }
        } else {
            System.out.println("Tento účet nepatří klientu " + client.getName());
        }
    }

    public void insertMoney(Company client) {
        System.out.println("Vybrána možnost: Přidat peníze na účet");
        System.out.println("Výpis všech účtů vlastněných klientem " + client.getName() + ".");
        System.out.println(client.listAccounts());
        System.out.println("Napište číslo účtu: ");
        String accNumin = sc.nextLine();
        while (!u.isDouble(accNumin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            accNumin = sc.nextLine();
        }
        System.out.println("Napište kolik chcete přidat: ");
        String moneyin = sc.nextLine();
        while (!u.isDouble(moneyin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            moneyin = sc.nextLine();
        }

        int accNum = Integer.parseInt(accNumin);
        Double money = Double.parseDouble(moneyin);

        if (u.isHis(client, accNum)) {
            if (u.addMoney(accNum, money)) {
                System.out.println("Na účet " + accNum + " bylo připsáno " + money + " kč.");
            } else {
                System.out.println("Něco se nepovedlo.");
            }
        } else {
            System.out.println("Tento účet nepatří klientu " + client.getName());
        }
    }

    public void withdrawMoney(Person client) {
        System.out.println("Vybrána možnost: Vybrat peníze z účtu");
        System.out.println("Výpis všech účtů vlastněných klientem " + client.getName() + ".");
        System.out.println(client.listAccounts());

        System.out.println("Odeberte peníze z účtu!");
        System.out.println("Napište číslo účtu: ");
        String accNumin = sc.nextLine();
        while (!u.isDouble(accNumin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            accNumin = sc.nextLine();
        }
        System.out.println("Napište kolik chcete vybrat: ");
        String moneyin = sc.nextLine();
        while (!u.isDouble(moneyin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            moneyin = sc.nextLine();
        }

        int accNum = Integer.parseInt(accNumin);
        Double money = Double.parseDouble(moneyin);

        if (u.isHis(client, accNum)) {
            if (u.withdrawMoney(accNum, money)) {
                System.out.println("Z účtu " + accNum + " bylo vybráno " + money + " kč.");
            } else {
                System.out.println("Nedostatek peněz na účtu.");
            }
        } else {
            System.out.println("Tento účet nepatří klientu " + client.getName());
        }
    }

    public void withdrawMoney(Company client) {
        System.out.println("Vybrána možnost: Vybrat peníze z účtu");
        System.out.println("Výpis všech účtů vlastněných klientem " + client.getName() + ".");
        System.out.println(client.listAccounts());

        System.out.println("Odeberte peníze z účtu!");
        System.out.println("Napište číslo účtu: ");
        String accNumin = sc.nextLine();
        while (!u.isDouble(accNumin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            accNumin = sc.nextLine();
        }
        System.out.println("Napište kolik chcete vybrat: ");
        String moneyin = sc.nextLine();
        while (!u.isDouble(moneyin)) {
            System.out.println("Nepovedlo se, zadejte znovu.");
            moneyin = sc.nextLine();
        }

        int accNum = Integer.parseInt(accNumin);
        Double money = Double.parseDouble(moneyin);

        if (u.isHis(client, accNum)) {
            if (u.withdrawMoney(accNum, money)) {
                System.out.println("Z účtu " + accNum + " bylo vybráno " + money + " kč.");
            } else {
                System.out.println("Nedostatek peněz na účtu.");
            }
        } else {
            System.out.println("Tento účet nepatří klientu " + client.getName());
        }
    }

    public void allAccInfo(Person client) {
        System.out.println("Vybrána možnost: Celkový zůstatek na všech účtech");
        System.out.println("Výpis všech účtů vlastněných klientem " + client.getName() + ".");
        System.out.println(client.listAccounts());

        int totalBalance = 0;
        for (int i = 0; i < client.getList().size(); i++) {
            totalBalance += client.getList().get(i).getBalance();
        }
        System.out.println("Celkový pozůstatek na všech účtech vlastněných klientem " + client.getName() + " je " + totalBalance + " kč.");
    }

    public void allAccInfo(Company client) {
        System.out.println("Vybrána možnost: Celkový zůstatek na všech účtech");
        System.out.println("Výpis všech účtů vlastněných klientem " + client.getName() + ".");
        System.out.println(client.listAccounts());

        int totalBalance = 0;
        for (int i = 0; i < client.getList().size(); i++) {
            totalBalance += client.getList().get(i).getBalance();
        }
        System.out.println("Celkový pozůstatek na všech účtech vlastněných klientem " + client.getName() + " je " + totalBalance + " kč.");
    }

    private static void displaySpace() {
        for (int i = 0; i < 30; i++) {
            System.out.println(" ");
        }
    }

    private static void displayMenu() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("===== MENU =====");
        System.out.println("1. Vytvořit klienta");
        System.out.println("2. Přihlásit se za klienta");
        System.out.println("3. Admin tools");
        System.out.println("quit - Ukončit program");
        System.out.println("================");
        System.out.print("Zadejte volbu: ");
    }

    private static void displayMenuUsr(String name) {
        System.out.println(" ");
        System.out.println("===== " + name + " =====");
        System.out.println("1. Vytvořit účet");
        System.out.println("2. Přidat peníze na účet");
        System.out.println("3. Vybrat peníze z účtu");
        System.out.println("4. Vypsat info o účtu ");
        System.out.println("5. Celkový zůstatek na všech účtech");
        System.out.println("6. Odhlásit se");
        System.out.println("================");
        System.out.print("Zadejte volbu: ");
    }

    private static void displayMenuAdmin() {
        System.out.println(" ");
        System.out.println("===== " + "ADMIN" + " =====");
        System.out.println("1. Vypsat klienty");
        System.out.println("2. Vypsat klienty podle peněz na účtu");
        System.out.println("3. Vypsat klienty podle jména(abecedně)");
        System.out.println("4. Odhlásit se");
        System.out.println("================");
        System.out.print("Zadejte volbu: ");
    }
}
