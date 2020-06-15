package cn.lijie;
public class MyTest {
    public void show() {
		B b = new B();
		System.out.println(b.getClass().getClassLoader());
        System.out.println("changed");
    }
}