package com.zy.composite;

/**
 * 组合模式 组合多个对象形成树形结构以表示有整体-部分关系层次结构，组合模式可以让客户端统一对待单个对象和组合对象 javaSE 中的AWT SWING 使用了这个模式
 * Component类是抽象构件，Checkbox、Button和TextComponent是叶子构件，而Container是容器构件
 * @author : 生态环境-张阳
 * @date : 2020/4/28 0028 11:45
 */
public class Test {
    public static void main(String[] args) {
        Composite composite = new Composite();
        composite.add(new InternetDepartment());
        composite.add(new SecurityDepartment());
        composite.operation();
    }
}
