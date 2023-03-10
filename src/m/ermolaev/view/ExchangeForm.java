package m.ermolaev.view;

import m.ermolaev.dataConversion.DataCollection;
import m.ermolaev.dataConversion.IDataCollection;
import m.ermolaev.remoteDataProvider.RemoteDataProvider;
import m.ermolaev.exchange.Exchange;
import m.ermolaev.exchange.IExchange;
import org.xml.sax.SAXException;
import m.ermolaev.dataConversion.IXMLCurrencyCollection;
import m.ermolaev.dataConversion.XMLCurrencyCollection;
import m.ermolaev.currency.ICurrency;
import m.ermolaev.remoteDataProvider.IRemoteDataProvider;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ExchangeForm {
    private JPanel mainPanel;
    private JComboBox sourceCurrencyComboBox;
    private JComboBox destinationCurrencyComboBox;
    private JTextField quantityTextField;
    private JTextField resultTextField;
    private JButton exchangeButton;

    private IRemoteDataProvider provider;
    private IDataCollection LastA;
    private IXMLCurrencyCollection xmlProvider;
    private IExchange exchange;

    private static ExchangeForm form;

    public ExchangeForm() {
        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value;
                    String sVal = quantityTextField.getText();
                    value = Math.abs(Double.parseDouble(sVal));
                    ICurrency from = (ICurrency) sourceCurrencyComboBox.getSelectedItem(); //error
                    ICurrency to = (ICurrency) destinationCurrencyComboBox.getSelectedItem();
                    double result = exchange.exchange(from, to, value);
                    resultTextField.setText(Double.toString(result));
                    quantityTextField.setText(Double.toString(value));
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Wrong transfer amount!");
                }
            }
        });
    }

    public static void initRates(ExchangeForm frm){
        frm.provider = new RemoteDataProvider();
        frm.LastA = new DataCollection();
        frm.xmlProvider = new XMLCurrencyCollection();
        frm.exchange  = new Exchange();
    }

    public static void requireDefault(ExchangeForm frm){
        try {
            String result = frm.provider.acquireRemoteData("https://api.nbp.pl/api/exchangerates/tables/a/?format=xml");
            frm.xmlProvider.provide(result, frm.LastA);
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }catch (SAXException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }catch (ParserConfigurationException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void initForm(ExchangeForm frm){
        int i = 0;
        List<ICurrency> cList = frm.LastA.getCurrencyList();
        for (i=0; i<cList.size(); i++){
            frm.sourceCurrencyComboBox.addItem(cList.get(i));
            frm.destinationCurrencyComboBox.addItem(cList.get(i));
        }
    }

    public static void main(String[] args){
        JFrame mainFrame = new JFrame("Currency exchanger");
        form = new ExchangeForm();
        mainFrame.setContentPane(form.mainPanel);
        initRates(form);
        requireDefault(form);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setMinimumSize(new Dimension(1000, 200));
        mainFrame.pack();
        mainFrame.setVisible(true);
        initForm(form);
    }
}
