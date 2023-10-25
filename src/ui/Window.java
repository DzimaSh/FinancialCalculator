package ui;

import calculator.Calculator;
import ui.buttons.ActionButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.UIUtils.mainDimension;

public class Window {
    private final JFrame frame;
    private JPanel mainPanel;
    private final Calculator calculator;

    private final List<ActionButton> actions = new ArrayList<>();
    private final List<JTextField> operands = new ArrayList<>();
    private final JTextField resultField = new JTextField();

    public Window() {
        frame = new JFrame("Financial Calculator");
        calculator = new Calculator();

        initialize();
    }

    private void initialize() {
        frame.setSize(mainDimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();

        mainPanel = new JPanel();
        mainPanel.setSize(mainDimension.getSize());
        mainPanel.setLayout(new GridBagLayout());

        JPanel queryPanel = new JPanel();
        addActionsAndOperandsToPanel(queryPanel);
        JLabel equalSign = new JLabel("=");
        queryPanel.add(equalSign);
        resultField.setEditable(false);
        queryPanel.add(resultField);
        mainPanel.add(queryPanel, gbc);

        JPanel authorPanel = new JPanel();
        JLabel author = new JLabel("Подготовлено Шушкевичем Дмитрием. 3 курс, 12 группа");
        authorPanel.add(author);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(authorPanel, BorderLayout.SOUTH);

    }

    private void addActionsAndOperandsToPanel(JPanel queryPanel) {
        GridBagConstraints gbc = new GridBagConstraints();

        IntStream.range(0, 2)
                .forEach(i -> operands.add(prepareOperand()));
        IntStream.range(0, 1)
                .forEach(i -> actions.add(prepareButton()));

        IntStream.range(0, Math.min(operands.size(), actions.size()))
                .forEach(i -> {
                    queryPanel.add(operands.get(i), gbc);
                    queryPanel.add(actions.get(i), gbc);
                });
        queryPanel.add(operands.getLast(), gbc);
    }

    private ActionButton prepareButton() {
        ActionButton button = new ActionButton();
        button.addActionListener(e -> handleChange());
        button.applyChangeActionListener();
        return button;
    }

    private JTextField prepareOperand() {
        JTextField operand = new JTextField(10);
        operand.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleChange();
            }
        });
        return operand;
    }

    private void handleChange() {
        try {
            String result = calculator.calculate(
                    operands.stream()
                            .map(JTextComponent::getText)
                            .collect(Collectors.toList()),
                    actions.stream()
                            .map(ActionButton::getCalculationAction)
                            .collect(Collectors.toList())
            );
            resultField.setText(result);

            mainPanel.revalidate();
            mainPanel.repaint();
        } catch (ParseException e) {
            e.printStackTrace();
            handleError();
        }
    }

    private void handleError() {

    }

    public void start() {
        frame.setVisible(true);
    }
}
