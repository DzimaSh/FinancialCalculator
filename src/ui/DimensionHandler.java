package ui;

import java.awt.*;

public class DimensionHandler {

    public static final Dimension mainDimension = new Dimension(600, 400);

    public static int getMainWidth() {
        return mainDimension.width;
    }

    public static int getMainHeight() {
        return mainDimension.height;
    }

    public static Dimension calculateRelativeSize(double shift) {
        return new Dimension((int) (getMainWidth() * shift), (int) (getMainHeight() * shift));
    }

    public static Dimension calculateRelativeSize(double widthShift, double heightShift) {
        return new Dimension((int) (getMainWidth() * widthShift), (int) (getMainHeight() * heightShift));
    }
}
