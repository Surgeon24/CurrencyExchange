package m.ermolaev.dataConversion;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface IXMLCurrencyCollection {
    public void provide(String input, IDataCollection output) throws IOException, SAXException, ParserConfigurationException;
}
