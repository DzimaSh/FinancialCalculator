package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Locale;

public class Parser {
    private static final BigDecimal MIN_VALUE = new BigDecimal("-1000000000000.000000");
    private static final BigDecimal MAX_VALUE = new BigDecimal("1000000000000.000000");

    public static BigDecimal parseValue(String number) throws NumberFormatException, ParseException {
        String normalizedNumber = number
                .replace(',', '.');
        if (normalizedNumber.toLowerCase(Locale.ROOT).contains("e")) {
            throw new ParseException("Невалидный ввод: научная нотация запрещена.", 0);
        }

        BigDecimal parsedValue;
        try {
            parsedValue = new BigDecimal(normalizedNumber);
        } catch (NumberFormatException e) {
            throw new ParseException("Невалидный ввод...", 0);
        }

        parsedValue.setScale(6, RoundingMode.UNNECESSARY);

        if (parsedValue.compareTo(MIN_VALUE) < 0 || parsedValue.compareTo(MAX_VALUE) > 0) {
            throw new NumberFormatException("Переполнение!");
        }

        return parsedValue;
    }
}
