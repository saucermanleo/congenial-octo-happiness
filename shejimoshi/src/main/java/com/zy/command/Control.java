package com.zy.command;


import java.util.Stack;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/6 0006 11:02
 */
public class Control {
    private Command[] commands;
    private Stack<Command> stack;

    public Control() {
        commands = new Command[9];
        Command noopCommand = new NoopCommand();
        for (int i = 0; i < commands.length; i++) {
            commands[i] = noopCommand;
        }
        stack = new Stack<>();
    }

    public void keyPressed(int index) {
        commands[index].execute();
        stack.push(commands[index]);
    }

    public Control setCommand(int index,Command command){
        commands[index] = command;
        return this;
    }

    public void rollback(int num){
        while(num>0){
            stack.pop().undo();
            num--;
        }
    }
}
