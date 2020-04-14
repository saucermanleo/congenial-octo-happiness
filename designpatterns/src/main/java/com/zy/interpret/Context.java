package com.zy.interpret;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 16:39
 */
public class Context {
    private StringTokenizer stringTokenizer;
    private String concurrentToken;
    private Stack<AbstractExpression> stack = new Stack<>();

    public Context(String command) {
        stringTokenizer = new StringTokenizer(command);
        init();
    }

    public int concurrentInt(){
        return Integer.parseInt(concurrentToken);
    }

    public String nextToken(){
        concurrentToken = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken():null;
        return concurrentToken;
    }

    private void init(){
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
        direction.interpret(this);
        Action action = new Action();
        action.interpret(this);
        Distance distance = new Distance();
        distance.interpret(this);
        Sentence sentence = new Sentence(direction,action,distance);
        return sentence;
    }

    public void execute(){
        stack.pop().execute();
    }
}
