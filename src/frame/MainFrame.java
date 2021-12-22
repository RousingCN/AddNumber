package frame;

import data.Data;
import panel.GamePanel;
import setting.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author RousingCN
 */
public class MainFrame extends JFrame implements ActionListener {
    static GamePanel gamePanel = new GamePanel();
    JPanel headPanel, mainPanel, bottomPanel;
    JLabel headTitle = new JLabel("加数字");
    JButton gameRule = new JButton("游戏规则");
    JButton gameRestart = new JButton("重置游戏");
    JLabel pointText = new JLabel("可分配点数为" + Data.POINT, SwingConstants.CENTER);
    public MainFrame() {
        super("四分五落");
        if (Data.mainFrame == null) {
            //保存对此对象的引用
            Data.mainFrame = this;
        }
        setSize(400, 450);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        new Setting().setCentered(this);

        headPanel = new JPanel();
        headPanel.add(headTitle);
        mainPanel = gamePanel.getGamePanel();
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(gameRestart, BorderLayout.WEST);
        bottomPanel.add(pointText, BorderLayout.CENTER);
        bottomPanel.add(gameRule, BorderLayout.EAST);

        Container contentPane = this.getContentPane();
        contentPane.add(headPanel, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        gameRule.addActionListener(this);
        gameRestart.addActionListener(this);
    }
    /**
     * 更新显示文本
     */
    public void updatePointText() {
        pointText.setText("可分配点数为" + Data.POINT);
    }
    /**
     * 更新主面板
     */
    public void updateMainPanel() {
        this.setVisible(true);
    }

    /**
     * 点击数字触发监听器事件
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameRule) {
            //打开游戏规则窗口
            JOptionPane.showMessageDialog(null, "默认有20个点数，每次点击格子里的数字都会消耗一个点数。\n" +
                    "当往数字4继续添加点数时，数字4会向四个方向分散，使四个方向上第一个遇到的数字+1。\n" +
                    "你的目标就是消除界面上的所有数字,每次消除全部的数字，都会获得一定的奖励点数。\n" +
                    "可获得点数=本关消除的格子数量/" + Data.MAGNIFICATION, "游戏规则", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == gameRestart) {
            Data.gamePanel.updateGamePanel();
        }
    }
}