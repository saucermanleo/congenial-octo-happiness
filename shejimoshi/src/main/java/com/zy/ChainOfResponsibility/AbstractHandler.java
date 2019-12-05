package com.zy.ChainOfResponsibility;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 15:01
 */
public abstract class AbstractHandler implements Handler {

    private String condition;
    private Handler handler;

    public AbstractHandler(String condition) {
        this.condition = condition;
    }

    public abstract void doHandle();

    @Override
    public void handle(String condition) {
        if (this.condition.equals(condition)) {
            this.doHandle();
        } else {
            if (this.handler != null) {
                this.handler.handle(condition);
            }
        }
    }

    @Override
    public Handler setHandler(Handler handler) {
        return this.handler = handler;
    }
}
