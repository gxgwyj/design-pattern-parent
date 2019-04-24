package com.xyz.designpattern.facade;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 类: ShowFlight <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月11日 16:38
 */
public class ShowFlight extends JPanel {

    /**
     * 计算轨迹显示轨迹的方法
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        // 绘制背景
        super.paintComponent(g);
        int nPoint = 101;
        double w = getWidth() - 1;
        double h = getHeight() - 1;
        int[] x = new int[nPoint];
        int[] y = new int[nPoint];

        for (int i = 0; i < nPoint; i++) {
            double t = ((double) i) / (nPoint - 1);
            x[i] = (int) (t * w);
            y[i] = (int) (4 * h * (t - .5) * (t - .5));
        }

        g.drawPolyline(x, y, nPoint);
    }

    public static TitledBorder createTitledBorder(String title) {
        TitledBorder tb = BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP);
        tb.setTitleColor(Color.BLACK);
        tb.setTitleFont(getStandardFont());
        return tb;
    }

    public static JPanel createTitledPanel(String title,JPanel in) {
        JPanel out = new JPanel();
        out.add(in);
        out.setBorder(createTitledBorder(title));
        return out;
    }

    public static Font getStandardFont() {
        return new Font("Dialog", Font.PLAIN, 18);
    }


    public static void main(String[] args) {
        ShowFlight flight = new ShowFlight();
        flight.setPreferredSize(new Dimension(300, 200));
        JPanel panel = createTitledPanel("Flight path", flight);
        JFrame frame = new JFrame("Flight Path for Shell Duds");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
