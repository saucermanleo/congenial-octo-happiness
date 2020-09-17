package com.bjmashibing.system.rpc.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/2 0002 11:51
 */
public class ClassReactUtil {
    private static Queue<File> queue = new LinkedList<>();
    private static Queue<String> queuePackage = new LinkedList<>();

    private static void listDictory(String packge, File destFile, boolean recive, Set<Class<?>> sets, Predicate<Class<?>> predicate) {
        String destPath;
        File[] files = destFile.listFiles();
        packge = packge.equals("")?packge:packge+".";
        for (File listFile : files) {
            if (listFile.isFile()) {
                destPath = listFile.getName();
                if (!destPath.endsWith(".class")) {
                    continue;
                }
                destPath = packge + destPath.replace(".class", "");
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
                queuePackage.offer(packge + listFile.getName());
            }
        }
    }


    private static void listJar(String packge, String path, boolean recursive, Set<Class<?>> sets, Predicate<Class<?>> predicate) {
        String[] jarInfo = URLDecoder.decode(path).split("!");
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        try {
            JarFile jarFile = new JarFile(jarFilePath);
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    if (true) {
                        if (entryName.startsWith(packge)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            Class<?> aClass = Class.forName(entryName);
                            if (predicate.test(aClass)) {
                                sets.add(aClass);
                            }
                        }
                    } else {
                        int index = entryName.lastIndexOf("/");
                        String myPackagePath;
                        if (index != -1) {
                            myPackagePath = entryName.substring(0, index);
                        } else {
                            myPackagePath = entryName;
                        }
                        if (myPackagePath.equals(packge)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            Class<?> aClass = Class.forName(entryName);
                            if (predicate.test(aClass)) {
                                sets.add(aClass);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<Class<?>> listClazz(Class<?> clazz, boolean recursive, Predicate<Class<?>> filter) throws IOException {
        Package aPackage = clazz.getPackage();
        String name = "";
        if(aPackage != null){
            name= clazz.getPackage().getName();
        }
        return ClassReactUtil.listClazz(name,recursive,filter);
    }

    public static Set<Class<?>> listClazz(String path, boolean recursive, Predicate<Class<?>> filter) throws IOException {
        Set<Class<?>> sets = new HashSet<>();

        String realPath = path.replace(".", "/");
        Enumeration<URL> resources = ClassReactUtil.class.getClassLoader().getResources(realPath);
        while (resources.hasMoreElements()) {

            URL currUrl = resources.nextElement();
            // 获取协议文本
            final String protocol = currUrl.getProtocol();

            if ("FILE".equalsIgnoreCase(protocol)) {
                queue.offer(new File(currUrl.getFile()));
                queuePackage.offer(path);
            } else if ("JAR".equalsIgnoreCase(protocol)) {
                listJar(path, currUrl.getPath(), recursive, sets, filter);
            }
        }

        while (!queue.isEmpty()) {
            listDictory(queuePackage.poll(), queue.poll(), recursive, sets, filter);
        }
        return sets;
    }


}
