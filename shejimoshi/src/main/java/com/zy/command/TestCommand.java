package com.zy.command;

/**
 * 命令模式把一个请求或者操作封装到一个对象中。命令模式允许系统使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。
 * 命令模式可以用来对点餐系统和命令系统封装
 *
 * @author : 生态环境-张阳
 * @date : 2019/12/6 0006 11:19
 */
public class TestCommand {

    public static void main(String[] args) {
        Control control = new Control();
        Door door = new Door();
        Light light = new Light();
        Computer computer = new Computer();
        control.setCommand(0, new CloseDoorCommand(door))
                .setCommand(1, new OpenDoorCommand(door))
                .setCommand(2, new LigntOnCommand(light))
                .setCommand(3, new LightOffCommand(light))
                .setCommand(4, new ComputerOnCommand(computer));


        CombinationCommand combinationCommand = new CombinationCommand();
        combinationCommand.add(new CloseDoorCommand(door))
                .add(new LightOffCommand(light))
                .add(new ComputerOnCommand(computer));
        control.setCommand(5, combinationCommand);

        control.keyPressed(0);
        control.keyPressed(1);
        control.keyPressed(2);
        control.keyPressed(3);
        control.keyPressed(4);
//        control.keyPressed(5);
//        control.keyPressed(6);
//        control.keyPressed(7);
//        control.keyPressed(8);

        control.rollback(3);
    }
}
