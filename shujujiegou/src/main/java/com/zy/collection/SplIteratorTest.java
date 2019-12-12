package com.zy.collection;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 通过Spliterator多线程遍历集合
 * @author : 生态环境-张阳
 * @date : 2019/12/12 0012 16:18
 */
public class SplIteratorTest {
    public static void main(String[] args) throws InterruptedException {

        List<Spliterator> spliterators;
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 32; i++) {
            list.add(i);
        }

        spliterators = split(list.spliterator().trySplit(), 2);
        System.out.println(spliterators.size());

        for (Spliterator spliterator : spliterators) {
            new Thread(()->{
                spliterator.forEachRemaining(x -> {
                    System.out.println(Thread.currentThread().getName()+":"+x);
                });
            }).start();


        }
    }

    public static void split(List<Spliterator> list) {
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            listIterator.add(((Spliterator) listIterator.next()).trySplit());
        }
    }
        /**
        * 吧spliterator分num次
        *
        * @param spliterator
        * @param num
        * @return : java.util.List<java.util.Spliterator>
        * @throws :
        * @author : 生态环境-张阳
        * @date : 2019/12/12 0012 16:19
        */
        public static List<Spliterator> split(Spliterator spliterator, int num) {
            List<Spliterator> spliterators = new ArrayList<>();
            spliterators.add(spliterator);
            ListIterator listIterator;
            for (int i = 1; i <= num; i++) {
                listIterator = spliterators.listIterator();
                while (listIterator.hasNext()) {
                    Spliterator s = ((Spliterator) listIterator.next()).trySplit();
                    if(s!=null) {
                        listIterator.add(s);
                    }else{
                        break;
                    }
                }
            }

            return spliterators;
        }
}
