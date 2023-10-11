package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class Parser {
    public static BigDecimal parseValue(String number) throws ParseException {
        NumberFormat numberFormat = DecimalFormat.getInstance();
        numberFormat.setMaximumFractionDigits(15);

        return BigDecimal.valueOf(numberFormat.parse(number).doubleValue());
    }
}
