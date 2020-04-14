package com.zy.interpret;

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
    void interpret(Context context) {

    }

    @Override
    void execute() {
        direction.execute();
        action.execute();
        distance.execute();
    }
}
