package com.xyz.designpattern.adapter.swing;

import javax.swing.table.AbstractTableModel;

/**
 * JTable组件是运用适配器模式的一个绝佳范例
 */
public class RocketTableModel extends AbstractTableModel {
    protected Rocket[] rockets;
    protected String[] columnNames = new String[]{"name","price","apogee"};

    public RocketTableModel(Rocket[] rockets) {
        this.rockets = rockets;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return rockets.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return rockets[rowIndex].getName();
            case 1:
                return rockets[rowIndex].getPrice();
            case 2:
                return rockets[rowIndex].getApogee();
            default:
                return null;
        }
    }
}
