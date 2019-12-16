package com.zy.command;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/6 0006 10:54
 */
public class CloseDoorCommand implements Command {
    private Door door;

    public CloseDoorCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

    @Override
    public void undo() {
        door.open();
    }
}
