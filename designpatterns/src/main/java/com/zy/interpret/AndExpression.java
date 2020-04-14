package com.zy.interpret;

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
    void interpret(Context context) {

    }

    @Override
    void execute() {
        left.execute();
        right.execute();
    }
}
