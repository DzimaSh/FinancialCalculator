package utils;

import java.awt.*;
import java.util.List;

public class UIUtils {
    public final static Dimension mainDimension = new Dimension(600, 400);

    public static void setPreferredSize(Component component) {
        if (component != null) {
            component.setSize(component.getPreferredSize());
        }
    }
}
