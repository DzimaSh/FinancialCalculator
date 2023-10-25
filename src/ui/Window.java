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

        JLabel errorArea = prepareErrorComponent();

        mainPanel = new JPanel();
        mainPanel.setSize(MAIN_DIMENSION);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(prepareQueryScrollPane(errorArea));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(errorArea);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(prepareAuthorPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private JComponent prepareQueryScrollPane(JLabel errorArea) {
        JPanel queryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel equalSign = new JLabel("=");

        addActionsAndOperandsToPanel(queryPanel, errorArea);
        queryPanel.add(equalSign);
        queryPanel.add(resultField);

        return queryPanel;
    }

    private void addActionsAndOperandsToPanel(JPanel queryPanel, JLabel errorArea) {
        IntStream.range(0, 2)
                .forEach(i -> operands.add(prepareOperand(errorArea)));
        IntStream.range(0, 1)
                .forEach(i -> actions.add(prepareButton(errorArea)));

        IntStream.range(0, Math.min(operands.size(), actions.size()))
                .forEach(i -> {
                    queryPanel.add(operands.get(i));
                    queryPanel.add(actions.get(i));
                });
        queryPanel.add(operands.getLast());
    }

    private ActionButton prepareButton(JLabel errorArea) {
        ActionButton button = new ActionButton();
        button.addActionListener(e -> handleChange(errorArea));
        button.applyChangeActionListener();
        return button;
    }

    private JTextField prepareOperand(JLabel errorArea) {
        JTextField operand = new JTextField(TEXT_FIELD_SIZE);

        operand.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleChange(errorArea);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleChange(errorArea);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleChange(errorArea);
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

    private JLabel prepareErrorComponent() {
        JLabel error = new JLabel();
        error.setForeground(Color.RED);

        return error;
    }

    private void handleChange(JLabel errorArea) {
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
            errorArea.setText("");

            mainPanel.revalidate();
            mainPanel.repaint();
        } catch (Exception e) {
            handleError(errorArea, e.getMessage());
        }
    }

    private void handleError(JLabel errorArea, String errorText) {
        errorArea.setText(errorText);
    }

    public void start() {
        frame.setVisible(true);
    }
}
