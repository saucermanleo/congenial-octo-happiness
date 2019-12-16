package com.zy.command;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/6 0006 11:27
 */
public class CombinationCommand implements Command {

    private List<Command> commands;

    public CombinationCommand() {
        this.commands = new LinkedList<>();
    }

    @Override
    public void execute() {
        commands.forEach(command -> {
            command.execute();
        });
    }

    @Override
    public void undo() {
        for(int k=commands.size()-1;k>=0;k--){
            commands.get(k).undo();
        }
    }

    public CombinationCommand add(Command command){
        commands.add(command);
        return this;
    }
}
