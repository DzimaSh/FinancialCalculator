package calculator;

import utils.Parser;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {

    public BigDecimal add(BigDecimal number1, BigDecimal number2) {
        return number1.add(number2);
    }

    public BigDecimal subtract(BigDecimal number1, BigDecimal number2) {
        return number1.subtract(number2);
    }

    public String calculate(List<String> operands, List<CalculationAction> actions) {
        return operands.toString();
    }
}
