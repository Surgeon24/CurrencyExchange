package main.view;

import main.dataConversion.IDataCollection;
import main.exchange.IExchange;
import main.currency.ICurrency;

import java.util.List;
import java.util.Scanner;

public class StandardView implements ICurrencyView {
    IExchange exchange;
    IDataCollection dataCollection;
    Scanner in;
    public StandardView() {
        this.in = new Scanner(System.in);
    }

    public void setExchange(IExchange exchange) {
        this.exchange = exchange;
    }

    public void setDataCollection(IDataCollection collection) {
        this.dataCollection = collection;
    }

    public void ViewAll(IDataCollection coll) {
        System.out.println("Currency\t\t\t\t\t\t\tcode\trate");
        System.out.println(dataCollection.ToString());
    }

    public ICurrency StringToCurrency(String code) {
        List<ICurrency> list = dataCollection.getCurrencyList();
        for (ICurrency cur : list) {
            if (cur.getCode().equals(code)) {
                return cur;
            }
        }
        return null;
    }

    public ICurrency ChooseCurrency(String label) {
        String line;
        ICurrency input;
        System.out.println(label);
        line = in.nextLine();
        input = StringToCurrency(line);
        if (input == null) {
            System.out.println("\nWrong code of the currency.\n");
            return null;
        }
        return input;
    }

    public void exchange() {
        ICurrency fromCurrency;
        ICurrency toCurrency;
        double amount = 0.0, result = 0.0;

        fromCurrency = ChooseCurrency("\nEnter the code of exchanged currency: ");
        if (fromCurrency == null)
            return;
        toCurrency = ChooseCurrency("\nEnter the code of the desired currency: ");
        if (toCurrency == null)
            return;
        System.out.println("\nEnter the amount of currency you want to exchange: ");
        amount = Double.parseDouble(in.nextLine());
        if (amount <= 0.0) {
            System.out.println("Wrong amount.\n");
            return;
        }
        result = exchange.exchange(fromCurrency, toCurrency, amount);

        System.out.println("\nAmount of your new currency: " + result + " (" + toCurrency.getCode() + ")" + "\n");
    }

    public void menu() {
        String choice;
        while(true) {
            System.out.println("1)To show list of currencies");
            System.out.println("2)To make an exchange");
            System.out.println("0)To exit");
            choice = in.nextLine();

            if (choice.equals("1"))
                ViewAll(dataCollection);
            else if (choice.equals("2"))
                exchange();
            else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("Wrong option.\n");
            }
        }
    }
}

