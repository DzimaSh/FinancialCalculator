package ui;

import calculator.Calculator;

import javax.swing.*;
import java.util.List;

public class Window {
    private JFrame frame;
    private List<JButton> actions;
    private List<JTextField> numbers;
    private JTextField result;
    private Calculator calculator;

    public Window() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private JTextField prepareResultField() {
        result = new JTextField();
        result.setEditable(false);

        return result;
    }

    public void start() {
        frame.setVisible(true);
    }
}
