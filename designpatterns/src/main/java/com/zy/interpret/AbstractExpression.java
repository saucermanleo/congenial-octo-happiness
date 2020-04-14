package com.zy.interpret;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 16:38
 */
public abstract class AbstractExpression {
    abstract void interpret(Context context);
    abstract void execute();
}
