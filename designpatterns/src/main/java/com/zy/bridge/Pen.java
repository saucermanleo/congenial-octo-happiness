package com.zy.bridge;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/27 0027 15:00
 */
public abstract class Pen {
    private Color color;

    public Pen(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void operation();
}
