package calculator;

import java.math.BigDecimal;

public class Calculator {
    public BigDecimal add(BigDecimal number1, BigDecimal number2) {
        return number1.add(number2);
    }

    public BigDecimal subtract(BigDecimal number1, BigDecimal number2) {
        return number1.subtract(number2);
    }
}
