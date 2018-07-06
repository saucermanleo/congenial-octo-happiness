package java8;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
/*
 * Java8 内置的四大核心函数式接口
 * java.util.function下
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class LamadaTest1 {
    /**
     * 消费型接口
     */
    @Test
    public void consumer(){
        Consumer<Integer> consumer = x-> System.out.println("消费了"+x);
        consumer.accept(5);
    }

    /**
     * 供给型接口
     */
    @Test
    public void supplier(){
        Supplier<Integer> supplier = ()->(int)(Math.random()*100);
        int generaget = supplier.get();
        System.out.println("生成了"+generaget);
    }

    /**
     * 断言接口
     */
    @Test
    public void predicate(){
        Predicate<String> predicate = (String)->String.equals("text");
        System.out.println(predicate.test("text"));
    }

    /**
     * 函数接口
     */
    @Test
    public void function(){
        Function<String,String> function = (x)->x.trim();
        System.out.println(function.apply("   test   "));
    }

}
