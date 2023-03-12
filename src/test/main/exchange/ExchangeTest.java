package main.exchange;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.currency.Currency;
import main.currency.ICurrency;

public class ExchangeTest {

    @Test
    public void testExchange() {
        ICurrency currency1 = new Currency();
        currency1.setName("dolar amerykański");
        currency1.setCode("USD");
        currency1.setRate(4.4266);

        ICurrency currency2 = new Currency();
        currency2.setName("euro");
        currency2.setCode("EUR");
        currency2.setRate(4.6838);

        IExchange exchange = new Exchange();
        double result = exchange.exchange(currency1, currency2, 100.0);
        assertEquals(94.508732226, result, 0.00001);
    }

}
