package main;

import main.dataConversion.DataCollection;
import main.dataConversion.IDataCollection;
import main.remoteDataProvider.RemoteDataProvider;
import main.exchange.Exchange;
import main.exchange.IExchange;
import main.view.ICurrencyView;
import main.dataConversion.IXMLCurrencyCollection;
import main.dataConversion.XMLCurrencyCollection;
import main.remoteDataProvider.IRemoteDataProvider;
import org.xml.sax.SAXException;
import main.view.StandardView;

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
