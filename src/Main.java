import ui.Window;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                Window window = new Window(600, 400);
                window.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
