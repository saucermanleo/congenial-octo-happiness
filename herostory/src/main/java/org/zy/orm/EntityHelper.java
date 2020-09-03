package org.zy.orm;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/3 0003 14:32
 */
public class EntityHelper {

    public static <T> T create(Class<T> clazz, ResultSet resultSet) throws IllegalAccessException, InstantiationException, SQLException {
        T result = (T) clazz.newInstance();
        Field[] declaredFields = clazz.getFields();
        for (Field declaredField : declaredFields) {
            Column annotation = declaredField.getAnnotation(Column.class);
            if (annotation == null) continue;
            declaredField.setAccessible(true);
            String value = annotation.value();
            declaredField.set(result, resultSet.getObject(value));
        }
        return result;
    }
}
