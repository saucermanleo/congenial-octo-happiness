package com.bjmashibing.system.test.threadnio;

/**
 * @author : 生态环境-张阳
 * @date : 2020/7/23 0023 15:33
 */
public class Test {
    public static void main(String[] args) {
        Boss boss =new Boss(3,3);
        boss.bind(6666);
        boss.bind(7777);
        boss.bind(8888);
        boss.bind(9090);
    }
}
