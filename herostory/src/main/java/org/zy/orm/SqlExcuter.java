package org.zy.orm;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/3 0003 11:15
 */
public class SqlExcuter {

    public static <T> List<T> excute(String sql, Class<T> clazz) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String dbConnStr = "jdbc:mysql://localhost:3306/ormtest?user=root&password=root";
            Connection conn = DriverManager.getConnection(dbConnStr);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            List<T> list = new LinkedList<>();
            AbstractEntityHelper helper = EntityHelperFactory.getHelper(clazz);

            while (resultSet.next()) {
                list.add((T)helper.create(resultSet));
            }
            return list;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
