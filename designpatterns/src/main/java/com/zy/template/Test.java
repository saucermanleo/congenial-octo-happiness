package com.zy.template;
//模板模式- 部分逻辑在父类模板方法中实现,其余逻辑用抽象方法调用,延迟到子类中实现.抽象方法用do开头 servlet的doService doPost doGet就是模板方法
public class Test {
    public static void main(String[] args) {
        Template t = new Template();
        t.templateMethod();
        TemplateA a = new TemplateA();
        a.templateMethod();
    }
}
