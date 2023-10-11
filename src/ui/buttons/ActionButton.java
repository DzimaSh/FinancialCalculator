package ui.buttons;

import calculator.CalculationAction;
import utils.Parser;
import utils.UIUtils;

import javax.swing.*;
import java.awt.*;

public class ActionButton extends JButton {
    protected CalculationAction calculationAction;

    public ActionButton() {
        super();
        this.calculationAction = CalculationAction.ADDITION;

        init();
    }

    public ActionButton(CalculationAction calculationAction) {
        super();
        this.calculationAction = calculationAction;

        init();
    }

    private void init() {
        this.setText(this.calculationAction.getValue());
        this.setFocusPainted(false);

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

    public CalculationAction getCalculationAction() {
        return calculationAction;
    }

    public void setCalculationAction(CalculationAction calculationAction) {
        this.calculationAction = calculationAction;
    }
}
