package java8;

import java8.Employee.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * 一、 Stream 的操作步骤
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class TestStream {
    @Test
    public void createStream() {
        //1 通过Collection接口提供的Stream() 或者 parallelStream()方法得到;
        List<String> list = Collections.emptyList();
        Stream<String> stream = list.stream();

        //2 通过Arrays中的静态方法stream()得到数组流
        Employee[] emps = new Employee[3];
        Stream<Employee> stream1 = Arrays.stream(emps);

        //3 通过Stream类中的静态方法
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 6, 5);

        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(15).forEach(System.out::println);
        //生成
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );
    //中间操作
    /*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */

    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void middleOpreation() {
        //所有的中间操作不会做任何的处理
        Stream<Employee> stream = emps.stream()
                .filter((e) -> {
                    System.out.println("测试中间操作");
                    return e.getAge() <= 35;
                });

        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        stream.forEach(System.out::println);
    }

    /*
		映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
    @Test
    public void mapTest() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        emps.stream().map(Employee::getName).forEach(System.out::println);

        list.stream().map(TestStream::filterCharacter).forEach(sm -> {
            sm.forEach(System.out::println);
        });
        System.out.println("-----------------------------------------------");
        list.stream().flatMap(TestStream::filterCharacter).forEach(System.out::println);

        //类似于List.add() 和 List.addAll();
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /*
		sorted()——自然排序
		sorted(Comparator com)——定制排序
	 */
    @Test
    public void testSort() {
        emps.stream().map(Employee::getName).sorted().forEach(System.out::println);

        emps.stream().sorted((x, y) -> {
            if (x.getAge() == (y.getAge())) {
                return x.getName().compareTo(y.getName());
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        }).forEach(System.out::println);
    }

    //3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void testMatch() {
        System.out.println(emps.stream().allMatch(e -> e.getStatus().equals(Status.BUSY)));
        System.out.println(emps.stream().anyMatch(e -> e.getStatus().equals(Status.BUSY)));
        System.out.println(emps.stream().noneMatch(e -> e.getStatus().equals(Status.BUSY)));
        System.out.println(emps.stream().count());
        System.out.println(emps.stream().max(Comparator.comparingDouble(Employee::getSalary)));
        Optional<Employee> e = emps.stream().sorted(Comparator.comparingDouble(Employee::getSalary)).findFirst();
        System.out.println(e.get());
    }

    /*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);

        System.out.println(sum);

        System.out.println("----------------------------------------");

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(op.get());

        System.out.println("----------------------------------------");

        Optional<Integer> count = emps.stream().map(Employee::getName).flatMap(TestStream::filterCharacter).map(x -> {
            if (x.equals('六')) {
                return 1;
            } else {
                return 0;
            }
        }).reduce(Integer::sum);
        System.out.println(count.get());
    }

    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void testCollect() {
        //集合
        List<String> namelist = emps.stream().map(Employee::getName).collect(Collectors.toList());
        namelist.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        Set<String> nameset = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        nameset.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        Set<String> hashsetname = emps.stream().map(Employee::getName).collect(Collectors.toCollection(TreeSet::new));
        hashsetname.forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        //总数
        emps.stream().collect(Collectors.counting());
        //平均值
        double avg = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        System.out.println("-----------------------------------------------");
        //总和
        double sum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        System.out.println("-----------------------------------------------");
        //分组
        Map<Status, List<Employee>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
        System.out.println("-----------------------------------------------");
    }

    @Test
    public void testcollect1() {
        //多级分组
        Map<Status, Map<String, List<Employee>>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
            if (e.getAge() > 50) {
                return "老年";
            } else {
                return "青年";
            }
        })));
        System.out.println(map);
        System.out.println("-----------------------------------------------");
        //分区
        Map<Boolean, List<Employee>> map1 = emps.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
        System.out.println(map1);
        System.out.println("-----------------------------------------------");
        //
        DoubleSummaryStatistics dss = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
        System.out.println(dss.getMax());
        System.out.println("-----------------------------------------------");
        //拼接字符串
        String s = emps.stream().map(Employee::getName).collect(Collectors.joining(",", "+++", "==="));
        System.out.println(s);
    }
}
