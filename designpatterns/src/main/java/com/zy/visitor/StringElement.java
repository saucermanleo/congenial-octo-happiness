package com.zy.visitor;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/10 0010 15:42
 */
public class StringElement implements Element {
    private String number = "0";

    public StringElement(String number) {
        this.number = number;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(number);
    }
}
