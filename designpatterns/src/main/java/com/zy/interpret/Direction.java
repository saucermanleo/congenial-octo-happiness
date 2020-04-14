package com.zy.interpret;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 17:02
 */
public class Direction extends AbstractExpression {

    private String operation;

    @Override
    void interpret(Context context) {
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
    void execute() {
        System.out.print(operation);
    }
}
