package com.zy.interpret.example;

import com.zy.interpret.AbstractContext;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 17:19
 */
public class AndExpression extends AbstractExpression {

    private AbstractExpression left;
    private AbstractExpression right;

    public AndExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void compile(Context context) {

    }

    @Override
    public void interpret() {
        left.interpret();
        right.interpret();
    }

    @Override
    public void interpret(AbstractContext context) {
        left.interpret(context);
        right.interpret(context);
    }
}
