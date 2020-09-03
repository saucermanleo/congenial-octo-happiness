package org.zy.orm;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.sql.ResultSet;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/3 0003 14:56
 */
public final class EntityHelperFactory {


    public static AbstractEntityHelper getHelper(Class<?> clazz) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        if (clazz == null) return null;
        ClassPool pool = ClassPool.getDefault();
        pool.appendSystemPath();

        pool.importPackage(ResultSet.class.getName());
        pool.importPackage(clazz.getName());

        CtClass ctClass = pool.getCtClass(AbstractEntityHelper.class.getName());
        String name = clazz.getName() + "EntityHelper";
        CtClass cc = pool.makeClass(name, ctClass);

        CtConstructor ctConstructor = new CtConstructor(new CtClass[0], cc);
        ctConstructor.setBody("{}");

        cc.addConstructor(ctConstructor);

        StringBuilder sb = new StringBuilder();

        sb.append("public Object create(java.sql.ResultSet resultSet){ ");
        sb.append(clazz.getName())
                .append(" result = ")
                .append("new " + clazz.getName() + "(); ");

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Column annotation = declaredField.getAnnotation(Column.class);
            if (annotation == null) continue;
            String value = annotation.value();
            String name1 = declaredField.getName();
            name1 = name1.substring(0, 1).toUpperCase() + name1.substring(1);

            if (declaredField.getType() == int.class) {
                sb.append("result.set")
                        .append(name1)
                        .append("(resultSet.getInt(\"")
                        .append(value)
                        .append("\")); ");
            }
            if (declaredField.getType().equals(String.class)) {
                sb.append("result.set")
                        .append(name1)
                        .append("(resultSet.getString(\"")
                        .append(value)
                        .append("\")); ");
            }

        }
        sb.append("return result; ");
        sb.append(" }");
        System.out.println(sb.toString());
        CtMethod ctMethod = CtMethod.make(sb.toString(), cc);
        cc.addMethod(ctMethod);
        //cc.writeFile("d:");
        Class aClass = cc.toClass();
        Object o = aClass.newInstance();
        return (AbstractEntityHelper) o;
    }
}
