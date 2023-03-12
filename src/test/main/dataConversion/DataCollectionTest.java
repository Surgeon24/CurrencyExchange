package main.dataConversion;

import main.currency.Currency;
import main.currency.ICurrency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataCollectionTest {

    @Test
    public void testToStringWithEmptyList() {
        DataCollection dataCollection = new DataCollection();
        String expected = "";
        String actual = dataCollection.ToString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWithOneCurrency() {
        DataCollection dataCollection = new DataCollection();
        ICurrency currency = new Currency();
        currency.setName("dolar amerykański");
        currency.setCode("USD");
        currency.setRate(4.4266);
        dataCollection.list.add(currency);
        String expected = "dolar amerykański                   (USD)  4.4266\n";
        String actual = dataCollection.ToString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWithMultipleCurrencies() {
        DataCollection dataCollection = new DataCollection();
        ICurrency currency1 = new Currency();
        currency1.setName("dolar amerykański");
        currency1.setCode("USD");
        currency1.setRate(4.4266);
        ICurrency currency2 = new Currency();
        currency2.setName("euro");
        currency2.setCode("EUR");
        currency2.setRate(4.6838);
        ICurrency currency3 = new Currency();
        currency3.setName("rupia indyjska");
        currency3.setCode("INR");
        currency3.setRate(0.053954);
        dataCollection.list.add(currency1);
        dataCollection.list.add(currency2);
        dataCollection.list.add(currency3);
        String expected = "dolar amerykański                   (USD)  4.4266\n" +
                "euro                                (EUR)  4.6838\n" +
                "rupia indyjska                      (INR)  0.053954\n";
        String actual = dataCollection.ToString();
        assertEquals(expected, actual);
    }

}