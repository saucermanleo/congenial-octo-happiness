package com.zy.interpret;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 16:47
 */
public class Distance extends AbstractExpression {

    private int distance;

    @Override
    void interpret(Context context) {
        context.nextToken();
        distance = context.concurrentInt();
    }

    @Override
    void execute() {
        System.out.println(distance+"米");
    }
}
