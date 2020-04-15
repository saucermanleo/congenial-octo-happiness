package com.zy.interpret.example;

import com.zy.interpret.AbstractContext;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 17:02
 */
public class Direction extends AbstractExpression {

    private String operation;

    @Override
    public void compile(Context context) {
        String concurrentToken = context.nextToken();
        if("up".equals(concurrentToken)){
            operation = "向上";
        }else if("left".equals(concurrentToken)) {
            operation = "向左";
        }else if("right".equals(concurrentToken)) {
            operation = "向右";
        }else if("down".equals(concurrentToken)) {
            operation = "向下";
        }
    }

    @Override
    public void interpret() {
        System.out.print(operation);
    }

    @Override
    public void interpret(AbstractContext context) {
        String concurrentToken = context.nextToken();
        if("up".equals(concurrentToken)){
            System.out.print("向上");
        }else if("left".equals(concurrentToken)) {
            System.out.print("向左");
        }else if("right".equals(concurrentToken)) {
            System.out.print("向右");
        }else if("down".equals(concurrentToken)) {
            System.out.print("向下");
        }
    }
}
