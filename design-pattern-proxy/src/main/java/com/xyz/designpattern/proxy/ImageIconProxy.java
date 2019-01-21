package com.xyz.designpattern.proxy;

import javax.swing.*;
import java.awt.*;

/**
 * 类: ImageIconProxy <br>
 * 描述: 图片代理类<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年09月14日 22:51
 */
public class ImageIconProxy extends ImageIcon implements Runnable {
    // 初始图片
    static final ImageIcon ABSENT = new ImageIcon(ClassLoader.getSystemResource("images/absent.jpg"));
    // 加载图片
    static final ImageIcon LOADING = new ImageIcon(ClassLoader.getSystemResource("images/loading.jpg"));

    // 代理的ImageIcon
    ImageIcon current = ABSENT;

    // 实际的超大图片
    protected String filename;
    protected JFrame callbackFrame;

    public ImageIconProxy(String filename){
        super(ABSENT.getImage());
        this.filename = filename;
    }

    public void load(JFrame callbackFrame) {
        this.callbackFrame = callbackFrame;
        current = LOADING;
        callbackFrame.repaint();
        new Thread(this).start();
    }

    public void run() {
        current = new ImageIcon(ClassLoader.getSystemResource(filename));
        callbackFrame.pack();
    }

    public int getIconHeight(){
        return super.getIconHeight();
    }

    @Override
    public int getIconWidth() {
        return super.getIconWidth();
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        super.paintIcon(c, g, x, y);
    }
}
