package setting;

import javax.swing.*;
import java.awt.*;

/**
 * @author RousingCN
 */
public class Setting {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    public Setting() {
    }

    /**
     * 使窗口居中
     * @param frame
     */
    public void setCentered(JFrame frame) {
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int screenWidth = toolkit.getScreenSize().width;
        int screenHeight = toolkit.getScreenSize().height;
        int frameX = (screenWidth - frameWidth) / 2;
        int frameY = (screenHeight - frameHeight) / 2;
        frame.setLocation(frameX, frameY);
    }
}
