package m.ermolaev.dataConversion;

import m.ermolaev.currency.ICurrency;

import java.util.List;

public interface IDataCollection {
    //Return all currencies with code and rate as one string
    String ToString();
    //Return reference on currency list
    List<ICurrency> getCurrencyList();
}
