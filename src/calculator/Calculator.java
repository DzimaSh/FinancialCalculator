package calculator;

import java.math.BigDecimal;

public interface Calculator {
    BigDecimal add(BigDecimal number1, BigDecimal number2);
    BigDecimal subtract(BigDecimal number1, BigDecimal number2);
}
