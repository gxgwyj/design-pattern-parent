package com.xyz.designpattern.observer.app;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * 具体的观察者
 */
public class ShowBallistics implements ChangeListener {

    /**
     * 滑块组件
     */
    protected JSlider slider;

    /**
     * 滑块最大值
     */
    protected double sliderMax;

    /**
     * 滑块最小值
     */
    protected double sliderMin;

    /**
     * 显示推力的面板
     */
    protected BallisticsPanel thrustPanel;

    /**
     * 显示燃烧率的面板
     */
    protected BallisticsPanel burnPanel;


    protected JLabel valueLabel;


    public static void main(String[] args) {
        SwingFacade.launch(new ShowBallistics().mainPanel(),
                "Effects of tPeak");
    }


    protected JPanel curvePanel() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        p.add(SwingFacade.createTitledPanel("Burn Rate", burnPanel()));
        p.add(SwingFacade.createTitledPanel("Thrust", thrustPanel()));
        return p;
    }

    protected JPanel mainPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(curvePanel(), "Center");
        p.add(sliderBox(), "South");
        return p;
    }

    public JSlider slider() {
        if (slider == null) {
            slider = new JSlider();
            sliderMax = slider.getMaximum();
            sliderMin = slider.getMinimum();
            slider.addChangeListener(this);
            slider.setValue(slider.getMinimum());
        }
        return slider;
    }

    protected Box sliderBox() {
        Box b = Box.createHorizontalBox();
        JLabel label = new JLabel("tPeak");
        label.setFont(SwingFacade.getStandardFont());
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        label.setForeground(java.awt.Color.black);
        b.add(label);
        b.add(valueLabel());
        b.add(slider());
        return b;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        double val = slider.getValue();
        double tp = (val - sliderMin) / (sliderMax - sliderMin);
        // 观察者模式的触发点，通知观察者
        burnPanel().setTPeak(tp);
        // 观察者模式的触发点，通知观察者
        thrustPanel().setTPeak(tp);
        valueLabel().setText(Format.formatToNPlaces(tp, 2));

    }

    protected JLabel valueLabel() {
        if (valueLabel == null) {
            valueLabel = new JLabel();
            valueLabel.setFont(SwingFacade.getStandardFont());
            valueLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            valueLabel.setForeground(java.awt.Color.black);
        }
        return valueLabel;
    }

    /**
     * 创建燃烧率面板
     * @return
     */
    protected BallisticsPanel burnPanel() {
        if (burnPanel == null) {
            burnPanel = new BallisticsPanel(Ballistics.rate());
            burnPanel.setPreferredSize(new Dimension(300, 200));
        }
        return burnPanel;
    }

    /**
     * 创建推力面板
     * @return
     */
    protected BallisticsPanel thrustPanel() {
        if (thrustPanel == null) {
            thrustPanel = new BallisticsPanel(Ballistics.thrust());
            thrustPanel.setPreferredSize(new Dimension(300, 200));
        }
        return thrustPanel;
    }
}
