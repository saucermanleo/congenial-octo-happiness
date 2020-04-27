package com.zy.bridge;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/27 0027 15:09
 */
public class LargePen extends Pen {

    public LargePen(Color color) {
        super(color);
    }

    @Override
    public void operation() {
        System.out.println("画一条"+this.getColor().getColor()+"粗线");
    }
}
