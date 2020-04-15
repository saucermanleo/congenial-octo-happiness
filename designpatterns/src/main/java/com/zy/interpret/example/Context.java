package com.zy.interpret.example;

import com.zy.interpret.AbstractContext;


/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 16:39
 */
public class Context extends AbstractContext {

    public Context(String command) {
        super(command);
    }

    @Override
    public void init(){
        while(stringTokenizer.hasMoreTokens() ){
            if(stack.isEmpty()){
                stack.push(makeSentence());
            }else {
                this.nextToken();
                AndExpression andExpression = new AndExpression(stack.pop(),makeSentence());
                stack.push(andExpression);
            }
        }
    }

    private Sentence makeSentence(){
        Direction direction = new Direction();
        direction.compile(this);
        Action action = new Action();
        action.compile(this);
        Distance distance = new Distance();
        distance.compile(this);
        Sentence sentence = new Sentence(direction,action,distance);
        return sentence;
    }

//    @Override
//    public void execute(){
//        stack.pop().interpret();
//    }

    @Override
    public void execute(){
        while(stringTokenizer.hasMoreTokens()){
            Direction direction = new Direction();
            direction.interpret(this);
            Action action = new Action();
            action.interpret(this);
            Distance distance = new Distance();
            distance.interpret(this);
        }
    }
}
