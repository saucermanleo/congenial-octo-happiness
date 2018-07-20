package java8;

import java8.Employee.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * һ�� Stream �Ĳ�������
 *
 * 1. ���� Stream
 *
 * 2. �м����
 *
 * 3. ��ֹ����
 */
public class TestStream {
    @Test
    public void createStream() {
        //1 ͨ��Collection�ӿ��ṩ��Stream() ���� parallelStream()�����õ�;
        List<String> list = Collections.emptyList();
        Stream<String> stream = list.stream();

        //2 ͨ��Arrays�еľ�̬����stream()�õ�������
        Employee[] emps = new Employee[3];
        Stream<Employee> stream1 = Arrays.stream(emps);

        //3 ͨ��Stream���еľ�̬����
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 6, 5);

        //����
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(15).forEach(System.out::println);
        //����
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }

    List<Employee> emps = Arrays.asList(
            new Employee(102, "����", 59, 6666.66, Status.BUSY),
            new Employee(101, "����", 18, 9999.99, Status.FREE),
            new Employee(103, "����", 28, 3333.33, Status.VOCATION),
            new Employee(104, "����", 8, 7777.77, Status.BUSY),
            new Employee(104, "����", 8, 7777.77, Status.FREE),
            new Employee(104, "����", 8, 7777.77, Status.FREE),
            new Employee(105, "����", 38, 5555.55, Status.BUSY)
    );
    //�м����
    /*
	  ɸѡ����Ƭ
		filter�������� Lambda �� �������ų�ĳЩԪ�ء�
		limit�����ض�����ʹ��Ԫ�ز���������������
		skip(n) ���� ����Ԫ�أ�����һ���ӵ���ǰ n ��Ԫ�ص�����������Ԫ�ز��� n �����򷵻�һ���������� limit(n) ����
		distinct����ɸѡ��ͨ����������Ԫ�ص� hashCode() �� equals() ȥ���ظ�Ԫ��
	 */

    //�ڲ��������������� Stream API �ڲ����
    @Test
    public void middleOpreation() {
        //���е��м�����������κεĴ���
        Stream<Employee> stream = emps.stream()
                .filter((e) -> {
                    System.out.println("�����м����");
                    return e.getAge() <= 35;
                });

        //ֻ�е�����ֹ����ʱ�����е��м������һ���Ե�ȫ��ִ�У���Ϊ��������ֵ��
        stream.forEach(System.out::println);
    }

    /*
		ӳ��
		map�������� Lambda �� ��Ԫ��ת����������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ�ء�
		flatMap��������һ��������Ϊ�����������е�ÿ��ֵ��������һ������Ȼ������������ӳ�һ����
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

        //������List.add() �� List.addAll();
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /*
		sorted()������Ȼ����
		sorted(Comparator com)������������
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

    //3. ��ֹ����
	/*
		allMatch��������Ƿ�ƥ������Ԫ��
		anyMatch��������Ƿ�����ƥ��һ��Ԫ��
		noneMatch��������Ƿ�û��ƥ���Ԫ��
		findFirst�������ص�һ��Ԫ��
		findAny�������ص�ǰ���е�����Ԫ��
		count������������Ԫ�ص��ܸ���
		max���������������ֵ
		min��������������Сֵ
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
		��Լ
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) �������Խ�����Ԫ�ط�������������õ�һ��ֵ��
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
            if (x.equals('��')) {
                return 1;
            } else {
                return 0;
            }
        }).reduce(Integer::sum);
        System.out.println(count.get());
    }

    //collect��������ת��Ϊ������ʽ������һ�� Collector�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
    @Test
    public void testCollect() {
        //����
        List<String> namelist = emps.stream().map(Employee::getName).collect(Collectors.toList());
        namelist.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        Set<String> nameset = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        nameset.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        Set<String> hashsetname = emps.stream().map(Employee::getName).collect(Collectors.toCollection(TreeSet::new));
        hashsetname.forEach(System.out::println);
        System.out.println("-----------------------------------------------");

        //����
        emps.stream().collect(Collectors.counting());
        //ƽ��ֵ
        double avg = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        System.out.println("-----------------------------------------------");
        //�ܺ�
        double sum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        System.out.println("-----------------------------------------------");
        //����
        Map<Status, List<Employee>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
        System.out.println("-----------------------------------------------");
    }

    @Test
    public void testcollect1() {
        //�༶����
        Map<Status, Map<String, List<Employee>>> map = emps.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
            if (e.getAge() > 50) {
                return "����";
            } else {
                return "����";
            }
        })));
        System.out.println(map);
        System.out.println("-----------------------------------------------");
        //����
        Map<Boolean, List<Employee>> map1 = emps.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
        System.out.println(map1);
        System.out.println("-----------------------------------------------");
        //
        DoubleSummaryStatistics dss = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
        System.out.println(dss.getMax());
        System.out.println("-----------------------------------------------");
        //ƴ���ַ���
        String s = emps.stream().map(Employee::getName).collect(Collectors.joining(",", "+++", "==="));
        System.out.println(s);
    }
}
