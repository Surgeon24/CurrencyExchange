package main.view;

import main.dataConversion.IDataCollection;
import main.exchange.IExchange;
import main.currency.ICurrency;

public interface ICurrencyView {
    //Setting references to objects of Exchange and DataCollection types
    public void setExchange(IExchange exchange);
    public void setDataCollection(IDataCollection collection);
    public void ViewAll(IDataCollection coll);
    //After entering the code, it creates a Currency object and searches the collection using it
    public ICurrency StringToCurrency(String code);
    //Asks user for a code and then call StringToCurrency method
    public ICurrency ChooseCurrency(String label);
    public void exchange();
    public void menu();
}



