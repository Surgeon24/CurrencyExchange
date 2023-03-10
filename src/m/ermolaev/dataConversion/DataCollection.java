package m.ermolaev.dataConversion;
import m.ermolaev.currency.ICurrency;

import java.util.ArrayList;
import java.util.List;

public class DataCollection implements IDataCollection{
    List<ICurrency> list = new ArrayList();

    public DataCollection() {
        list = new ArrayList<>();
    }

    public String ToString(){
        String out = "";
        ICurrency tmp;
        for (ICurrency iCurrency : list) {
            tmp = iCurrency;
            int length = tmp.getName().length();
            String space = " ";
            for (int j = 0; j < 35 - length; j++) {
                space += " ";
            }
            out = out + tmp.getName() + space + "(" + tmp.getCode() + ")  " + tmp.getRate() + "\n";
        }
        return out;
    }


    public List<ICurrency> getCurrencyList(){
        return this.list;
    }
}
