package panel;

import data.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @author RousingCN
 */
public class Grid extends JButton implements ActionListener {
    int gridNum;
    JLabel numText;
    int gridIdX,gridIdY;

    public Grid(int idX,int idY) {
        this.gridIdX = idX;
        this.gridIdY = idY;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        gridNum = new Random().nextInt(Data.MAX);
        numText = new JLabel(String.valueOf(gridNum), JLabel.CENTER);
        if (gridNum == 0) {
            this.setEnabled(false);
        } else {
            numText.setText(String.valueOf(gridNum));
            this.add(numText, BorderLayout.CENTER);
            this.addActionListener(this);
        }
    }
    /**
     * 清空格子
     */
    public void clearGridNum() {
        this.setEnabled(false);
        numText.setText(null);
        Data.DESTROY++;
        Data.DESTROYNUM++;
    }
    /**
     * 获取游戏格子
     * @return Grid
     */
    public Grid getGrid() {
        return this;
    }
    /**
     * 更新游戏格子
     */
    public void updateGrid() {
        gridNum++;
        numText.setText(String.valueOf(gridNum));
        if (gridNum == Data.MAX) {
            clearGridNum();
            //溅射其他格子
            Data.updateGridNum(gridIdX,gridIdY);
        }
    }
    /**
     * 检查格子是否处于激活状态
     * @return boolean类型
     */
    public boolean checkGrid() {
        if (this.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGrid();
        //Point点数减少
        Data.decreasePoint();
    }
}
