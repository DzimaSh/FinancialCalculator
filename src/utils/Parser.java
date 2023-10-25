package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Parser {
    private static final BigDecimal MIN_VALUE = new BigDecimal("-1000000000000.000000");
    private static final BigDecimal MAX_VALUE = new BigDecimal("1000000000000.000000");

    public static BigDecimal parseValue(String number) throws NumberFormatException {
        String normalizedNumber = number
                .replace(',', '.');
        BigDecimal parsedValue = new BigDecimal(normalizedNumber);

        parsedValue = parsedValue.setScale(6, RoundingMode.UNNECESSARY);

        if (parsedValue.compareTo(MIN_VALUE) < 0 || parsedValue.compareTo(MAX_VALUE) > 0) {
            throw new NumberFormatException("Parsed value is out of range");
        }

        return parsedValue;
    }
}
