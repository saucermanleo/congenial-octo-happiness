package com.zy.command;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/6 0006 10:56
 */
public class LigntOnCommand implements Command {
    private Light light;

    public LigntOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
