package com.zy.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式  适用于对collection中有不同对象(不同的element实现)的不同操作(不同的visitor实现)
 * @author : 生态环境-张阳
 * @date : 2020/4/10 0010 15:51
 */
public class Test {
    public static void main(String[] args) {
        List<Element> list = new ArrayList<>();
        list.add(new FloatElement(4.5f));
        list.add(new StringElement("4.56"));
        list.add(new IntElement(9));

        SumVisitor sumV = new SumVisitor();
        Visitor typeV = new TypeVisitor();

        for (Element element : list) {
            element.accept(sumV);
            element.accept(typeV);
        }

        System.out.println(sumV.getSum());
    }
}
