package com.xyz.designpattern.observer.app;

import javax.swing.*;
import java.awt.Graphics;

/**
 * 面板（显示组件，该面板组件中包含计算轨迹的公式接口，而非实现了，体现了Java设计中的单一职责，松耦合的原则）
 */
public class BallisticsPanel extends JPanel {


    /**
     * 计算公式
     */
    protected BallisticsFunction func;

    /**
     * 坐标个数（点数）
     */
    protected int nPoint = 101;

    /**
     * 燃烧时间参数
     */
    protected double tPeak = 0.0;

    protected int[] x = new int[nPoint];

    protected int[] y = new int[nPoint];




    /**
     * 构造方法
     * @param function
     */
    public BallisticsPanel(BallisticsFunction function) {
        this.func = function;
    }

    public void setTPeak(double tPeak){
        this.tPeak = tPeak;
        repaint();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < nPoint ; i++) {
            double t = ((double) i) / (nPoint - 1);
            x[i] = (int) (t * getWidth());
            y[i] = (int) (getHeight() * (1 - func.function(t, tPeak)));
        }
        graphics.drawPolyline(x, y, nPoint);
    }
}
