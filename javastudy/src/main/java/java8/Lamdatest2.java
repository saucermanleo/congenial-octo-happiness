package java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;


    /*
     * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
     * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
     *
     * 1. 对象的引用 :: 实例方法名
     *
     * 2. 类名 :: 静态方法名
     *
     * 3. 类名 :: 实例方法名
     *
     * 注意：
     * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
     * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
     *
     * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
     *
     * 1. 类名 :: new
     *
     * 三、数组引用
     *
     * 	类型[] :: new;
     *
     *
     */
    public class Lamdatest2 {
        private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        private static final int COUNT_BITS = Integer.SIZE - 3;
        private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

        // runState is stored in the high-order bits
        private static final int RUNNING    = -1 << COUNT_BITS;
        private static final int SHUTDOWN   =  0 << COUNT_BITS;
        private static final int STOP       =  1 << COUNT_BITS;
        private static final int TIDYING    =  2 << COUNT_BITS;
        private static final int TERMINATED =  3 << COUNT_BITS;

        // Packing and unpacking ctl
        private static int runStateOf(int c)     { return c & ~CAPACITY; }
        private static int workerCountOf(int c)  { return c & CAPACITY; }
        private static int ctlOf(int rs, int wc) { return rs | wc; }
        //数组引用
        @Test
        public void test8(){
            Function<Integer, String[]> fun = (args) -> new String[args];
            String[] strs = fun.apply(10);
            System.out.println(strs.length);

            System.out.println("--------------------------");

            Function<Integer, Employee[]> fun2 = Employee[] :: new;
            Employee[] emps = fun2.apply(20);
            System.out.println(emps.length);
        }

        //构造器引用
        @Test
        public void test7(){
            Function<String, Employee> fun = Employee::new;

            BiFunction<String, Integer, Employee> fun2 = Employee::new;
        }

        @Test
        public void test6(){
            Supplier<Employee> sup = () -> new Employee();
            System.out.println(sup.get());

            System.out.println("------------------------------------");

            Supplier<Employee> sup2 = Employee::new;
            System.out.println(sup2.get());
        }

        //类名 :: 实例方法名
        @Test
        public void test5(){
            BiPredicate<String, String> bp = (x, y) -> x.equals(y);
            System.out.println(bp.test("abcde", "abcde"));

            System.out.println("-----------------------------------------");

            BiPredicate<String, String> bp2 = String::equals;
            System.out.println(bp2.test("abc", "abc"));

            System.out.println("-----------------------------------------");


            Function<Employee, String> fun = (e) -> e.show();
            System.out.println(fun.apply(new Employee()));

            System.out.println("-----------------------------------------");

            Function<Employee, String> fun2 = Employee::show;
            System.out.println(fun2.apply(new Employee()));

        }

        //类名 :: 静态方法名
        @Test
        public void test4(){
            Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

            System.out.println("-------------------------------------");

            Comparator<Integer> com2 = Integer::compare;
        }

        @Test
        public void test3(){
            BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
            System.out.println(fun.apply(1.5, 22.2));

            System.out.println("--------------------------------------------------");

            BiFunction<Double, Double, Double> fun2 = Math::max;
            System.out.println(fun2.apply(1.2, 1.5));
        }

        //对象的引用 :: 实例方法名
        @Test
        public void test2(){
            Employee emp = new Employee(101, "张三", 18, 9999.99);

            Supplier<String> sup = () -> emp.getName();
            System.out.println(sup.get());

            System.out.println("----------------------------------");

            Supplier<String> sup2 = emp::getName;
            System.out.println(sup2.get());
        }

        @Test
        public void test1(){
            PrintStream ps = System.out;
            Consumer<String> con = (str) -> ps.println(str);
            con.accept("Hello World！");

            System.out.println("--------------------------------");

            Consumer<String> con2 = ps::println;
            con2.accept("Hello Java8！");

            Consumer<String> con3 = System.out::println;
        }
        /*@Test
        public void test10(){
            *//*private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
            private static final int COUNT_BITS = Integer.SIZE - 3;
            private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

            // runState is stored in the high-order bits
            private static final int RUNNING    = -1 << COUNT_BITS;
            private static final int SHUTDOWN   =  0 << COUNT_BITS;
            private static final int STOP       =  1 << COUNT_BITS;
            private static final int TIDYING    =  2 << COUNT_BITS;
            private static final int TERMINATED =  3 << COUNT_BITS;*//*
            System.out.println("COUNT_BITS:"+COUNT_BITS+":"+Integer.toString(COUNT_BITS,2));
            System.out.println("CAPACITY:"+CAPACITY+":"+Integer.toString(CAPACITY,2));
            System.out.println("RUNNING:"+RUNNING+":"+Integer.toString(RUNNING,2));
            System.out.println("SHUTDOWN:"+SHUTDOWN+":"+Integer.toString(SHUTDOWN,2));
            System.out.println("STOP:"+STOP+":"+Integer.toString(STOP,2));
            System.out.println("TIDYING:"+TIDYING+":"+Integer.toString(TIDYING,2));
            System.out.println("TERMINATED:"+TERMINATED+":"+Integer.toString(TERMINATED,2));
            System.out.println(workerCountOf(ctl.get()));
        }*/

    }
