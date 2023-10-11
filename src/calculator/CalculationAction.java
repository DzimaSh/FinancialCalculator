package calculator;

public enum CalculationAction {
    ADDITION("+"),
    SUBTRACTION("-");

    private final String value;

    CalculationAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
