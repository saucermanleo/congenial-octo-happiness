package com.zy.visitor;



/**
 * @author : 生态环境-张阳
 * @date : 2020/4/10 0010 15:39
 */
public class IntElement implements Element {

    private int number;

    public IntElement(int number) {
        this.number = number;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(number);
    }
}
