package juc;

import java.util.concurrent.locks.LockSupport;

public class ExampleTest {
    static Thread t1, t2 ,t3= null;

    /*public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHI".toCharArray();

        t2 = new Thread(() -> {
            for (char x : aI) {
                LockSupport.park();
                System.out.println(x);
                LockSupport.unpark(t1);


            }
        });

        t1 = new Thread(() -> {
            for (char x : aC) {
                System.out.println(x);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t1.start();
        t2.start();
    }*/

    public static void main(String[] args) {
        char[] aI = "123456789".toCharArray();
        char[] aC = "ABCDEFGHI".toCharArray();
        char[] ad = "abcdefghi".toCharArray();

        t1 = new Thread(() -> {
            for (char x : aI) {
                System.out.print(x);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (char x : aC) {
                LockSupport.park();
                System.out.print(x);
                LockSupport.unpark(t3);
            }
        });

        t3 = new Thread(() -> {
            for (char x : ad) {
                LockSupport.park();
                System.out.print(x);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }



}
