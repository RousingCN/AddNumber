import data.Data;
import frame.MainFrame;

/**
 * @author RousingCN
 */
public class Startup {

    public static void main(String[] args) {
        //游戏初始化
        Data.init();
        //打开面板
        new MainFrame().setVisible(true);
    }
}
