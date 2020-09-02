package org.zy.tinygame.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/2 0002 11:51
 */
public class ClassReactUtil {
    private static Queue<File> queue = new LinkedList<>();
    private static Queue<String> queuePackage = new LinkedList<>();

    private static void listDictory(String packge, File destFile, boolean recive, Set<Class<?>> sets, Predicate<Class<?>> predicate) {
        String destPath;
        for (File listFile : destFile.listFiles()) {
            if (listFile.isFile()) {
                destPath = listFile.getName();
                if (!destPath.endsWith(".class")) {
                    continue;
                }
                destPath = packge + "." + destPath.replace(".class", "");
                try {
                    Class<?> aClass = Class.forName(destPath);
                    if (predicate.test(aClass)) {
                        sets.add(aClass);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            } else if (listFile.isDirectory() && recive) {
                queue.offer(listFile);
                queuePackage.offer(packge + "." + listFile.getName());
            }
        }
    }

    public static Set<Class<?>> listClazz(String path, boolean recursive, Predicate<Class<?>> filter) throws IOException {
        Set<Class<?>> sets = new HashSet<>();

        String realPath = path.replace(".", "/");
        Enumeration<URL> resources = ClassReactUtil.class.getClassLoader().getResources(realPath);
        File destFile = new File(resources.nextElement().getFile());
        queue.offer(destFile);
        queuePackage.offer(path);
        while (!queue.isEmpty()) {
            listDictory(queuePackage.poll(), queue.poll(), recursive, sets, filter);
        }
        return sets;
    }


}
