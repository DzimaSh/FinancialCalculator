package calculator;

import utils.Parser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public class Calculator {

    public BigDecimal add(BigDecimal number1, BigDecimal number2) {
        return number1.add(number2);
    }

    public BigDecimal subtract(BigDecimal number1, BigDecimal number2) {
        return number1.subtract(number2);
    }

    public String calculate(List<String> operands, List<CalculationAction> actions) throws ParseException {
        if (operands.isEmpty()) {
            throw new IllegalArgumentException("Operands list is empty.");
        }

        if (actions.isEmpty()) {
            throw new IllegalArgumentException("Actions list is empty.");
        }

        BigDecimal result = Parser.parseValue(operands.get(0));

        if (operands.size() != actions.size() + 1) {
            throw new IllegalArgumentException("Number of operands and actions does not match.");
        }

        for (int i = 0; i < actions.size(); i++) {
            BigDecimal operand2 = Parser.parseValue(operands.get(i + 1));

            CalculationAction action = actions.get(i);

            result = switch (action) {
                case ADDITION -> add(result, operand2);
                case SUBTRACTION -> subtract(result, operand2);
            };
        }

        return result.toString();
    }
}
