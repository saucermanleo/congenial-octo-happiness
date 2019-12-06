package com.zy.command;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/6 0006 10:58
 */
public class ComputerOnCommand implements Command {
    private Computer computer;

    public ComputerOnCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.on();
    }

    @Override
    public void undo() {
        computer.off();
    }
}
