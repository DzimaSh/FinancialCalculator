package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class Parser {
    private static final BigDecimal MIN_VALUE = new BigDecimal("-1000000000000.000000");
    private static final BigDecimal MAX_VALUE = new BigDecimal("1000000000000.000000");

    public static BigDecimal parseValue(String number) throws ParseException {
        BigDecimal parsedValue = new BigDecimal(number);

        if (parsedValue.compareTo(MIN_VALUE) < 0 || parsedValue.compareTo(MAX_VALUE) > 0) {
            throw new ParseException("Parsed value is out of range", 0);
        }

        return parsedValue;
    }
}
