package com.zy.ChainOfResponsibility;

/**
 * 责任链模式可以解决请求与处理解耦,一个请求可以遍历选择一个处理器来处理   与状态模式的区别在于:
 * 1.状态模式的逻辑流程已经确定了的(编译的时候已经确定),而责任链的逻辑可以在客户端随时变化组合.
 * 2.状态模式运行到哪个模式可以确定,而责任链不知道哪个handler处理了请求
 * 3.表现在状态模式的content聚合了state,而且state的方法中放入了content,用来重置content的状态
 * 责任链的逻辑组装推迟到content中 要使用时临时组合如:Handler handler = new MonHandler();
 * handler.setHandler(new TueHandler()).setHandler(new WedHandler());
 *
 * @author : 生态环境-张阳
 * @date : 2019/12/5 0005 15:15
 */
public class Test {
    public static void main(String[] args) {
        //编写责任链的逻辑链
        Handler handler = new MonHandler();
        handler.setHandler(new TueHandler()).setHandler(new WedHandler());
        handler.handle("mon");
    }
}
