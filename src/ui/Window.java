package ui;

import calculator.Calculator;
import ui.buttons.ActionButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.UIUtils.*;

public class Window {
    private final JFrame frame;
    private JPanel mainPanel;
    private final Calculator calculator;

    private final List<ActionButton> actions = new ArrayList<>();
    private final List<JTextField> operands = new ArrayList<>();
    private final JLabel resultField = new JLabel();

    public Window() {
        frame = new JFrame("Financial Calculator");
        calculator = new Calculator();

        initialize();
    }

    private void initialize() {
        frame.setSize(MAIN_DIMENSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setSize(MAIN_DIMENSION);
        mainPanel.add(prepareQueryScrollPane());

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(prepareAuthorPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private JComponent prepareQueryScrollPane() {
        JPanel queryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel equalSign = new JLabel("=");

        addActionsAndOperandsToPanel(queryPanel);
        queryPanel.add(equalSign);
        queryPanel.add(resultField);

        return queryPanel;
    }

    private void addActionsAndOperandsToPanel(JPanel queryPanel) {
        IntStream.range(0, 2)
                .forEach(i -> operands.add(prepareOperand()));
        IntStream.range(0, 1)
                .forEach(i -> actions.add(prepareButton()));

        IntStream.range(0, Math.min(operands.size(), actions.size()))
                .forEach(i -> {
                    queryPanel.add(operands.get(i));
                    queryPanel.add(actions.get(i));
                });
        queryPanel.add(operands.getLast());
    }

    private ActionButton prepareButton() {
        ActionButton button = new ActionButton();
        button.addActionListener(e -> handleChange());
        button.applyChangeActionListener();
        return button;
    }

    private JTextField prepareOperand() {
        JTextField operand = new JTextField(TEXT_FIELD_SIZE);

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

    private JPanel prepareAuthorPanel() {
        JPanel authorPanel = new JPanel();
        JLabel author = new JLabel(AUTHOR_LABEL);
        authorPanel.add(author);

        return authorPanel;
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
            System.out.println(e.getMessage());
            handleError();
        }
    }

    private void handleError() {

    }

    public void start() {
        frame.setVisible(true);
    }
}
