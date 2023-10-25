package utils;

import java.awt.*;

public class UIUtils {
    public final static Dimension MAIN_DIMENSION = new Dimension(400, 100);
    public final static Integer TEXT_FIELD_SIZE = 10;
    public final static String AUTHOR_LABEL = "Подготовлено Шушкевичем Дмитрием. 3 курс, 12 группа";

    public static void setPreferredSize(Component component) {
        if (component != null) {
            component.setSize(component.getPreferredSize());
        }
    }
}
