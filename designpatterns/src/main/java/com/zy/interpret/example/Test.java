package com.zy.interpret.example;

/**
 * 解释器模式  主要用于解释自定义语言
 * @author : 生态环境-张阳
 * @date : 2020/4/14 0014 17:54
 */
public class Test {
    public static void main(String[] args) {
        Context context = new Context("up move 5 and left run 7 and down move 4 and right run 5");
        context.execute();
    }
}
