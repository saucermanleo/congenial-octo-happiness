package com.zy.interpret;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 17:08
 */
public class Action extends AbstractExpression {

    private String operation;
    @Override
    void interpret(Context context) {
        String concurrentToken = context.nextToken();
        if("move".equals(concurrentToken)){
            operation = "移动";
        }else if("run".equals(concurrentToken)){
            operation = "快速移动";
        }
    }

    @Override
    void execute() {
        System.out.print(operation);
    }
}
