package com.xyz.designpattern.facade;

import javax.swing.*;
import java.awt.*;

/**
 * 类: ShowOptionPane <br>
 * 描述: 外观模式的测试<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月11日 16:24
 */
public class ShowOptionPane {
    public static void main(String[] args) {
        Font font = new Font("Dialog", Font.PLAIN, 18);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);

        int option;
        do {
            option = JOptionPane.showConfirmDialog(null, "Had enough?", "A Stubborn Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        } while (option == JOptionPane.NO_OPTION);
    }
}
