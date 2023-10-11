package ui.buttons;

import calculator.CalculationAction;
import utils.Parser;

import javax.swing.*;

public class ActionButton extends JButton {
    protected final Parser parser;
    protected CalculationAction calculationAction;

    public ActionButton(Parser parser) {
        super();
        this.parser = parser;
        this.calculationAction = CalculationAction.ADDITION;

        this.setText(CalculationAction.ADDITION.getValue());

        addActionListener();
    }

    public ActionButton(Parser parser, CalculationAction calculationAction) {
        super();
        this.parser = parser;
        this.calculationAction = calculationAction;

        this.setText(calculationAction.getValue());

        addActionListener();
    }

    private void addActionListener() {
        this.addActionListener((event) -> {
            CalculationAction[] actions = CalculationAction.values();
            int currentActionOrdinal = this.getCalculationAction().ordinal(),
                    actionsLength = actions.length;

            if (currentActionOrdinal >= actionsLength - 1) {
                this.setCalculationAction(actions[0]);
            } else {
                this.setCalculationAction(actions[++currentActionOrdinal]);
            }
            this.setText(this.getCalculationAction().getValue());
        });
    }

    public Parser getParser() {
        return parser;
    }

    public CalculationAction getCalculationAction() {
        return calculationAction;
    }

    public void setCalculationAction(CalculationAction calculationAction) {
        this.calculationAction = calculationAction;
    }
}
