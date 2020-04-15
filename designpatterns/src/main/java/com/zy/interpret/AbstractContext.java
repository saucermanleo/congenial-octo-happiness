package com.zy.interpret;

import com.zy.interpret.example.*;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/15 0015 11:00
 */
public abstract class AbstractContext {
    public StringTokenizer stringTokenizer;
    public String concurrentToken;
    public Stack<AbstractExpression> stack = new Stack<>();

    public AbstractContext(String command) {
        stringTokenizer = new StringTokenizer(command);
        init();
        stringTokenizer = new StringTokenizer(command);
    }

    public int concurrentInt() {
        return Integer.parseInt(concurrentToken);
    }

    public String nextToken() {
        concurrentToken = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : null;
        return concurrentToken;
    }

    abstract public void init();


    abstract public void execute();

    public StringTokenizer getStringTokenizer() {
        return stringTokenizer;
    }

    public String getConcurrentToken() {
        return concurrentToken;
    }

    public Stack<AbstractExpression> getStack() {
        return stack;
    }
}
