package com.zy.interpret.example;

import com.zy.interpret.AbstractContext;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 16:47
 */
public class Distance extends AbstractExpression {

    private int distance;

    @Override
    public void compile(Context context) {
        context.nextToken();
        distance = context.concurrentInt();
    }

    @Override
    public void interpret() {
        System.out.println(distance+"米");
    }

    @Override
    public void interpret(AbstractContext context) {
        context.nextToken();
        System.out.println(context.concurrentInt()+"米");
        context.nextToken();
    }
}
