package m.ermolaev.dataConversion;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import m.ermolaev.currency.Currency;
import m.ermolaev.currency.ICurrency;

public class XMLCurrencyCollection implements IXMLCurrencyCollection {
    @Override
    public void provide(String input, IDataCollection output) throws IOException, SAXException, ParserConfigurationException {
        Document doc = inputToDocument(input);
        XMLToCurrencyList(doc, output);
    }
    private String getStringByName(Element e, String name){
        return e.getElementsByTagName(name)
                .item(0)
                .getTextContent();
    }

    private double getDoubleByName(Element e, String name){
        return Double.parseDouble(getStringByName(e,name).replace(",","."));
    }
    private ICurrency elementToICurrency(Element e){
        ICurrency currency = new Currency();
        String code = getStringByName(e,"Code");
        String name = getStringByName(e, "Currency");
        double rate = getDoubleByName(e,"Mid");
        currency.setCode(code);
        currency.setName(name);
        currency.setRate(rate);
        return currency;
    }


    private void addZloty(IDataCollection output){
        Currency zloty = new Currency();
        zloty.setCode("PLN");
        zloty.setRate(1);
        zloty.setName("Złoty nowy");

        output.getCurrencyList().add(zloty);
    }

    private void XMLToCurrencyList(Document doc, IDataCollection output){
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Rate");
        Node tempNode;

        addZloty(output);

        for(int i=0;i< nList.getLength();i++){
            tempNode = nList.item(i);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) tempNode;
                ICurrency tCurrency = this.elementToICurrency(eElement);
                output.getCurrencyList().add(tCurrency);
            }
        }
    }


    private Document inputToDocument(String input) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringReader reader = new StringReader(input);
        InputSource iSource = new InputSource(reader);
        Document document = builder.parse(iSource);
        return document;

    }
}
