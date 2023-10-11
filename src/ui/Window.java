package ui;

import calculator.CalculationAction;
import calculator.Calculator;
import ui.buttons.ActionButton;
import utils.Parser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static ui.DimensionHandler.calculateRelativeSize;
import static ui.DimensionHandler.getMainWidth;

public class Window {
    private final JFrame frame;
    private final Parser parser;
    private final Calculator calculator;
    private final Dimension mainDimension;

    private List<ActionButton> actions = new ArrayList<>();
    private List<JTextField> operands = new ArrayList<>();

    public Window(int width, int height) {
        frame = new JFrame();
        parser = new Parser();
        calculator = new Calculator();
        mainDimension = new Dimension(width, height);

        initialize();
    }

    private void initialize() {
        frame.setSize(mainDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(mainDimension.getSize());
        mainPanel.setLayout(new GridBagLayout());

        JPanel queryPanel = new JPanel();
        queryPanel.setSize(calculateRelativeSize(0.75, 0.2));
        queryPanel.setLayout(new BoxLayout(queryPanel, BoxLayout.X_AXIS));
        addActionsAndOperandsToPanel(queryPanel);
        mainPanel.add(queryPanel);

        frame.getContentPane().add(mainPanel);
    }

    private JPanel addActionsAndOperandsToPanel(JPanel queryPanel) {
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.fill = GridBagConstraints.HORIZONTAL;
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 0;
        gbc_button.gridy = 10;

        GridBagConstraints gbc_operand = new GridBagConstraints();
        gbc_operand.fill = GridBagConstraints.HORIZONTAL;
        gbc_operand.insets = new Insets(0, 0, 5, 5);
        gbc_operand.gridx = 0;
        gbc_operand.gridy = 0;

        IntStream.range(0, 2)
                .forEach(i -> {
                    JTextField operand = new JTextField(10);
                    operands.add(operand);
                });
        IntStream.range(0, 1)
                .forEach(i -> actions.add(prepareButton()));

        IntStream.range(0, Math.min(operands.size(), actions.size()))
                .forEach(i -> {
                    queryPanel.add(operands.get(i), gbc_operand);
                    queryPanel.add(actions.get(i), gbc_button);
                });
        queryPanel.add(operands.getLast(), gbc_operand);
        return queryPanel;
    }

    private JTextField prepareResultField() {
        JTextField result = new JTextField();
        result.setEditable(false);

        return result;
    }

    private ActionButton prepareButton() {
        return new ActionButton(parser);
    }


    public void start() {
        frame.setVisible(true);
    }
}
