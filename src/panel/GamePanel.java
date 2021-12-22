package panel;

import data.Data;

import javax.swing.*;
import java.awt.*;

/**
 * @author RousingCN
 */
public class GamePanel {
    public static JPanel panel= new JPanel();;
    int row, col;
    Grid[][] grids;
    public GamePanel() {
        if (Data.gamePanel == null) {
            Data.gamePanel = this;
        }
        row = Data.ROW;
        col = Data.COL;
        grids = Data.GRIDS;
        panel.setLayout(new GridLayout(7, 7));
        startGamePanel();
    }
    /**
     * 开始游戏
     */
    public void startGamePanel() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                panel.add(grids[i][j].getGrid());
            }
        }
    }
    /**
     * 重置游戏
     */
    public void updateGamePanel() {
        Data.init();
        panel.removeAll();
        startGamePanel();
        Data.mainFrame.updatePointText();
        Data.mainFrame.updateMainPanel();
        System.out.println("重置游戏完成");
    }
    /**
     * 获取游戏面板
     * @return JPanel
     */
    public JPanel getGamePanel() {
        return panel;
    }
}
