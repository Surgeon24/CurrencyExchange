package main.dataConversion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import main.currency.Currency;
import main.currency.ICurrency;


public class XMLCurrencyCollectionTest {

    private IXMLCurrencyCollection converter;
    private IDataCollection output;

    @BeforeEach
    public void setUp() {
        converter = new XMLCurrencyCollection();
        output = new DataCollection();
    }

    @Test
    public void testProvide() throws Exception {
        String xml = "<ExchangeRatesTable>" +
                "<Table>" +
                "<EffectiveDate>2022-03-11</EffectiveDate>" +
                "<No>050/A/NBP/2022</No>" +
                "</Table>" +
                "<Rate>" +
                "<Currency>bat (Tajlandia)</Currency>" +
                "<Code>THB</Code>" +
                "<Mid>0.1293</Mid>" +
                "</Rate>" +
                "<Rate>" +
                "<Currency>dolar amerykański</Currency>" +
                "<Code>USD</Code>" +
                "<Mid>3.7547</Mid>" +
                "</Rate>" +
                "<Rate>" +
                "<Currency>nowy izraelski szekel</Currency>" +
                "<Code>ILS</Code>" +
                "<Mid>1.1551</Mid>" +
                "</Rate>" +
                "</ExchangeRatesTable>";

        converter.provide(xml, output);

        List<ICurrency> expectedCurrencies = new ArrayList<>();
        Currency zloty = new Currency();
        zloty.setCode("PLN");
        zloty.setRate(1);
        zloty.setName("Złoty nowy");
        expectedCurrencies.add(zloty);

        Currency thb = new Currency();
        thb.setCode("THB");
        thb.setRate(0.1293);
        thb.setName("bat (Tajlandia)");
        expectedCurrencies.add(thb);

        Currency usd = new Currency();
        usd.setCode("USD");
        usd.setRate(3.7547);
        usd.setName("dolar amerykański");
        expectedCurrencies.add(usd);

        Currency ils = new Currency();
        ils.setCode("ILS");
        ils.setRate(1.1551);
        ils.setName("nowy izraelski szekel");
        expectedCurrencies.add(ils);

        assertNotSame(expectedCurrencies, output.getCurrencyList());
        assertEquals(expectedCurrencies, output.getCurrencyList());
    }

}
