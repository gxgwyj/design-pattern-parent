package com.xyz.designpattern.adapter.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 模拟数据适配器
 */
public class ShowRocketTable {
    public static void main(String[] args) {

        setFonts();
        JTable table = new JTable(getRocketTable());
        table.setRowHeight(36);
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new java.awt.Dimension(300,100));
        display(pane,"Rockets");
    }

    public static void display(Component c, String title) {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(c);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();;
        frame.setVisible(true);
    }

    private static RocketTableModel getRocketTable() {
        Rocket[] rockets = new Rocket[3];
        for (int i = 0; i < 3; i++) {
            rockets[i] = new Rocket("name" + i, "price" + i, "apogee" + i);
        }
        return new RocketTableModel(rockets);
    }

    private static void setFonts() {
        Font font = new Font("Dialog", Font.PLAIN, 18);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
    }
}
