package com.zy.interpret.example;

import com.zy.interpret.AbstractContext;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 17:08
 */
public class Action extends AbstractExpression {

    private String operation;
    @Override
    public void compile(Context context) {
        String concurrentToken = context.nextToken();
        if("move".equals(concurrentToken)){
            operation = "移动";
        }else if("run".equals(concurrentToken)){
            operation = "快速移动";
        }
    }

    @Override
    public void interpret() {
        System.out.print(operation);
    }

    @Override
    public void interpret(AbstractContext context) {
        String concurrentToken = context.nextToken();
        if("move".equals(concurrentToken)){
            System.out.print("移动");
        }else if("run".equals(concurrentToken)){
            System.out.print("快速移动");
        }
    }
}
