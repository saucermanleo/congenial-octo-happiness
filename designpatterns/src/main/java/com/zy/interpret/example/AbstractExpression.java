package com.zy.interpret.example;

import com.zy.interpret.AbstractContext;
import com.zy.interpret.example.Context;

/**
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 16:38
 */
public abstract class AbstractExpression {
    /**
    * 编译
    *
    * @param context
    * @return : void
    * @throws :
    * @author : 生态环境-张阳
    * @date : 2020/4/15 0015 11:09
    */
    abstract public void compile(Context context);

    /**
    * 编译后解释执行
    *
    * @param
    * @return : void
    * @throws :
    * @author : 生态环境-张阳
    * @date : 2020/4/15 0015 11:09
    */
    abstract public void interpret();

    /**
     * 解释执行
     *
     * @param
     * @return : void
     * @throws :
     * @author : 生态环境-张阳
     * @date : 2020/4/15 0015 11:09
     */
    abstract public void interpret(AbstractContext context);
}
