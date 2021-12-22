package data;

import frame.GameOverFrame;
import frame.MainFrame;
import panel.GamePanel;
import panel.Grid;

/**
 * @author RousingCN
 */
public class Data {
    //每一个格子的最大数为MAX
    public static final int MAX = 5;
    //有ROW行格子
    public static final int ROW = 7;
    //有COL列格子
    public static final int COL = 7;
    //获得奖励点数的比例 = (1/MAGNIFICATION)
    public static final int MAGNIFICATION = 5;

    //游戏是否处于结束状态
    public static boolean GAMEOVER;
    //可分配点数
    public static int POINT;
    //这一关消除了几个数字
    public static int DESTROY;
    //这一局游戏已经完成了几个关卡
    public static int LEVELS;
    //这一局游戏已经消除了几个数字
    public static int DESTROYNUM;
    //保存对对象的引用
    public static final Grid[][] GRIDS = new Grid[ROW][COL];
    public static MainFrame mainFrame;
    public static GamePanel gamePanel;
    public static GameOverFrame gameOverFrame;

    /**
     * 游戏数据初始化
     */
    public static void init() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                GRIDS[i][j] = new Grid(i,j);
            }
        }
        GAMEOVER = false;
        POINT = 20;
        DESTROY = 0;
        LEVELS = 0;
        DESTROYNUM = 0;
    }
    /**
     * Point点数减少
     */
    public static void decreasePoint() {
        POINT--;
        //更新显示文本
        mainFrame.updatePointText();
        if (POINT == 0) {
            boolean noNext = checkAllGrid();
            if (noNext) {
                //游戏结束
                GAMEOVER = true;
                gameOver();
                //结束数据面板
                if (gameOverFrame != null) {
                    //关闭旧面板
                    gameOverFrame.dispose();
                }
                //创建新面板
                gameOverFrame = new GameOverFrame();
                gameOverFrame.setVisible(true);
                System.out.println("游戏结束");
            }

        }
    }
    /**
     * 游戏结束
     */
    public static void gameOver() {
        for (int i = 0; i < GRIDS.length; i++) {
            for (int j = 0; j < GRIDS[i].length; j++) {
                GRIDS[i][j].setEnabled(false);
            }
        }
    }
    /**
     * 溅射
     * @param x
     * @param y
     */
    public static void updateGridNum(int x, int y) {
        //左溅射
        if (y > 0) {
            //通过循环获得左方处于激活状态的格子
            for (int i = y - 1; i >= 0; i--) {
                if (GRIDS[x][i].checkGrid()) {
                    GRIDS[x][i].updateGrid();
                    break;
                }
            }
        }
        //右溅射
        if (y < COL - 1) {
            //通过循环获得右方处于激活状态的格子
            for (int i = y + 1; i < COL; i++) {
                if (GRIDS[x][i].checkGrid()) {
                    GRIDS[x][i].updateGrid();
                    break;
                }
            }
        }
        //上溅射
        if (x > 0) {
            //通过循环获得上方处于激活状态的格子
            for (int i = x - 1; i >= 0; i--) {
                if (GRIDS[i][y].checkGrid()) {
                    GRIDS[i][y].updateGrid();
                    break;
                }
            }
        }
        //下溅射
        if (x < ROW - 1) {
            //通过循环获得下方处于激活状态的格子
            for (int i = x + 1; i < ROW; i++) {
                if (GRIDS[i][y].checkGrid()) {
                    GRIDS[i][y].updateGrid();
                    break;
                }
            }
        }
        //检查是否还存在数字
        checkAllGrid();
    }
    /**
     * 检查所有格子的状态，是否触发下一关
     */
    public static boolean checkAllGrid() {
        int i;
        //检查所有格子
        for (i = 0; i < GRIDS.length; i++) {
            for (int j = 0; j < GRIDS[i].length; j++) {
                if (GRIDS[i][j].checkGrid()) {
                    return true;
                }
            }
        }
        System.out.println(i);
        System.out.println(GRIDS.length);
        //下一关
        if (!GAMEOVER) {
            nextLevel();
        }
        return false;

    }

    public static void nextLevel() {
        //触发下一关
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                GRIDS[i][j] = new Grid(i,j);
            }
        }
        //计算奖励点数
        POINT += DESTROY / MAGNIFICATION;
        System.out.println(POINT);
        DESTROY = DESTROY % MAGNIFICATION;
        LEVELS++;
        //刷新面板上的格子
        System.out.println("下一关，当前已完成" + LEVELS + "关");
        GamePanel.panel.removeAll();
        gamePanel.startGamePanel();
        mainFrame.updatePointText();
        mainFrame.updateMainPanel();
    }
}
