package com.zy.visitor;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/10 0010 15:43
 */
public class FloatElement implements Element {

    private Float number;

    public FloatElement(Float number) {
        this.number = number;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(number);
    }
}
