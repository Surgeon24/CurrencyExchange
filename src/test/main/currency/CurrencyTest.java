package main.currency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CurrencyTest {

    @Test
    public void testEquals() {
        Currency currency1 = new Currency();
        currency1.setName("dolar amerykański");
        currency1.setCode("USD");
        currency1.setRate(4.4266);

        Currency currency2 = new Currency();
        currency2.setName("dolar amerykański");
        currency2.setCode("USD");
        currency2.setRate(4.4266);

        Currency currency3 = new Currency();
        currency3.setName("forint (Węgry)");
        currency3.setCode("HUF");
        currency3.setRate(0.012235);

        assertTrue(currency1.equals(currency2));
        assertFalse(currency1.equals(currency3));
    }

}
