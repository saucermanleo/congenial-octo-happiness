package com.zy.command;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/6 0006 10:57
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
