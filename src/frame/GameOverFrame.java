package frame;

import data.Data;
import setting.Setting;

import javax.swing.*;
import java.awt.*;

/**
 * @author RousingCN
 */
public class GameOverFrame extends JFrame {
    JPanel panel01 = new JPanel(new BorderLayout());
    JPanel panel02 = new JPanel(new BorderLayout());
    JPanel panel03 = new JPanel(new BorderLayout());
    JPanel panel04 = new JPanel(new BorderLayout());
    JLabel title = new JLabel("游戏结束", SwingConstants.CENTER);
    JLabel titleText = new JLabel("在本次游戏里", SwingConstants.CENTER);
    JLabel levels = new JLabel("完成了" + Data.LEVELS + "个关卡", SwingConstants.CENTER);
    JLabel destroy = new JLabel("消除了" + Data.DESTROYNUM + "个数字", SwingConstants.CENTER);

    public GameOverFrame() {
        super("游戏结束");
        setSize(250, 300);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        new Setting().setCentered(this);

        //第一行
        title.setFont(new Font("微软雅黑", Font.BOLD, 24));
        panel01.add(title);
        //第二行
        titleText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        panel02.add(titleText);
        //第三行
        levels.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        panel03.add(levels);
        //第四行
        destroy.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        panel04.add(destroy);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(5, 1));
        contentPane.add(panel01);
        contentPane.add(panel02);
        contentPane.add(panel03);
        contentPane.add(panel04);
    }
}
