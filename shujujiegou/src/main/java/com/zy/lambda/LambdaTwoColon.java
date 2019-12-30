package com.zy.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author : 生态环境-张阳
 * @date : 2019/12/19 0019 17:25
 */
public class LambdaTwoColon {
    public static void main(String[] args) {
        //参数与返回值相同
        Consumer<String> consumer = System.out::println;
        Comparator<Integer> comparator = Integer::compare;

        //当实例方法的第一个参数为实例方法的调用者，第二个参数为实例方法的参数时，且实例方法的参数数量与类型、返回值类型与函数式接口一致时
        BiPredicate<String, String> bp = String::equals;

        //当构造器的参数列表与函数式接口的参数列表一致时
        Supplier supplier = ArrayList::new;
        Function<Integer,ArrayList<String>> function = ArrayList<String>::new;
        Function<Integer, Float[]> fun = Float[]::new;

        List list1 = function.apply(5);
        consumer.accept("4");
    }
}
