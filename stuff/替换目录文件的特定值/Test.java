package com.zdww.eemp.gov.logistics.biz.internal.cloud.building;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : 生态环境-张阳
 * @date : 2020/6/10 0010 12:04
 */
public class Test {

    public static Map<String ,String> map  = new HashMap();
    public static Map<String ,String> map1  = new HashMap();
    public static List<String> list = new LinkedList<>();
    public static List<String> list1 = new LinkedList<>();

    public static void main(String[] args) {
        String path = "d:/test11";
        //13.66
        map.put( "width\\:\\s(\\d+)px","width: z2yvm");
        map.put( "height\\:\\s(\\d+)px","height: z2yfm");

        //list1.add("");

        Test test = new Test();
        File file = new File("d:/zy/");
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            test.folderMethod(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void folderMethod(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        folderMethod(file2.getAbsolutePath());
                    } else {
                        if(this.canHandle(file2.getName())){
                            System.out.println("文件:" + file2.getAbsolutePath());
                            this.handle(file2);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

	public String replaceLast(String s,String t,String ns ){
        int n =  s.lastIndexOf(t);
        String temp = s.substring(0,n);
        String temp1 = s.substring(n);
        temp1 = temp1.replaceFirst(t,ns);
        return temp+temp1;
    }
	
    public void handle(File file) throws IOException {
        String pattern = "width\\:\\s(\\d+)px";
        Pattern r = Pattern.compile(pattern);

        String path = file.getAbsolutePath();
        path = "d:/zy"+path.substring(path.indexOf("\\"), path.length());
        File tempFile = new File(path);
        if(!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdirs();
        }
        tempFile.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String s;
        while ((s = bufferedReader.readLine()) != null) {

            s = handleString(s);
            bufferedWriter.write(s);
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();
    }

    public boolean canHandle(String name){
        String substring = name.substring(name.indexOf("."), name.length());
        return substring.equals(".html") || substring.equals(".css") ? true:false;
    }

    public String handleString(String s){
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String pattern = entry.getKey();
            Pattern p1 = Pattern.compile(pattern);
            Matcher m = p1.matcher(s);
            while (m.find()) {
                String n = m.group(1);
                float f = Float.valueOf(n);
                f = f / 13.66f;
                String z2y = entry.getValue().replace("z2y", Float.toString(Math.round(f * 100f) / 100f));
                s = s.replace(m.group(),   z2y);
            }
        }

        Set<Map.Entry<String, String>> entries1 = map1.entrySet();
        for (Map.Entry<String, String> entry : entries1) {
            String pattern = entry.getKey();
            Pattern p1 = Pattern.compile(pattern);
            Matcher m = p1.matcher(s);
            while (m.find()) {
                String n = m.group(1);
                float f = Float.valueOf(n);
                f = f / 3.6f;
                String z2y = entry.getValue().replace("z2y", Float.toString(Math.round(f * 100f) / 100f));
                s = s.replace(m.group(),   z2y);
            }
        }

        for (String s1 : list) {
            String pattern =s1;
            Pattern p1 = Pattern.compile(pattern);
            Matcher m = p1.matcher(s);
            while (m.find()) {
                String n = m.group(1);
                String g = m.group();
                float f = Float.valueOf(n);
                f = f / 3.6f;
                s = s.replace(m.group(),   g.replace(n,Float.toString(Math.round(f * 100f) / 100f)).replace("px","vw"));
            }
        }

        for (String s1 : list1) {
            String pattern =s1;
            Pattern p1 = Pattern.compile(pattern);
            Matcher m = p1.matcher(s);
            while (m.find()) {
                String n = m.group(1);
                String g = m.group();
                float f = Float.valueOf(n);
                f = f / 3.6f;
                s = s.replace(m.group(),   g.replace(n,Float.toString(Math.round(f * 100f) / 100f)).replace("px","vh"));
            }
        }
        return s;
    }
}
