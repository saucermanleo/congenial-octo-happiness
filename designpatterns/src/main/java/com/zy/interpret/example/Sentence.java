package com.zy.interpret.example;

import com.zy.interpret.AbstractContext;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 17:15
 */
public class Sentence extends AbstractExpression {

    private AbstractExpression direction;

    private AbstractExpression action;

    private AbstractExpression distance;

    public Sentence(AbstractExpression direction, AbstractExpression action, AbstractExpression distance) {
        this.direction = direction;
        this.action = action;
        this.distance = distance;
    }

    @Override
    public void compile(Context context) {

    }

    @Override
    public void interpret() {
        direction.interpret();
        action.interpret();
        distance.interpret();
    }

    @Override
    public void interpret(AbstractContext context) {
        direction.interpret(context);
        action.interpret(context);
        distance.interpret(context);
    }
}
