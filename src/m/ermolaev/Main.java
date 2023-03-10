package m.ermolaev;

import m.ermolaev.dataConversion.DataCollection;
import m.ermolaev.dataConversion.IDataCollection;
import m.ermolaev.remoteDataProvider.RemoteDataProvider;
import m.ermolaev.exchange.Exchange;
import m.ermolaev.exchange.IExchange;
import m.ermolaev.view.ICurrencyView;
import org.xml.sax.SAXException;
import m.ermolaev.dataConversion.IXMLCurrencyCollection;
import m.ermolaev.dataConversion.XMLCurrencyCollection;
import m.ermolaev.remoteDataProvider.IRemoteDataProvider;
import m.ermolaev.view.StandardView;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Main {

    static IRemoteDataProvider provider;
    static IDataCollection LastA;
    static IXMLCurrencyCollection xmlProvider;
    static IExchange exchange;
    static ICurrencyView view;

    public static void main(String[] args) {
        provider = new RemoteDataProvider();
        xmlProvider = new XMLCurrencyCollection();

        LastA = new DataCollection();
        exchange = new Exchange();
        view = new StandardView();
        try {
            String result = provider.acquireRemoteData("https://api.nbp.pl/api/exchangerates/tables/a/?format=xml");
            xmlProvider.provide(result,LastA);

            view.setDataCollection(LastA);
            view.setExchange(exchange);
            view.menu();

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }

    }
}
