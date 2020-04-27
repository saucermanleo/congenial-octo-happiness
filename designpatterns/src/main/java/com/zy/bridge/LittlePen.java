package com.zy.bridge;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/27 0027 15:15
 */
public class LittlePen extends Pen {

    public LittlePen(Color color) {
        super(color);
    }

    @Override
    public void operation() {
        System.out.println("画一条"+this.getColor().getColor()+"细线");
    }
}
