package com.zy.strategy;
//封装变化的概念 多态 comparotor接口  lamda表达式就是策略方法的表现,只是不用写很多策略类
public class Test {
    public static void main(String[] args) {
        Strategy s = new AddStrategy();
        Environment e = new Environment(s);
        System.out.println(e.calculate(1,3));
        e.setStrategy(new SubtraStrategy());
        System.out.println(e.calculate(1,3));
        e.setStrategy((a,b)-> a*b);
        System.out.println(e.calculate(1,3));
    }
}
