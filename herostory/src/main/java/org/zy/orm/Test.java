package org.zy.orm;

import javassist.CannotCompileException;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/3 0003 11:30
 */
public class Test {

    public static void main(String[] args) {
        try {
            EntityHelperFactory.getHelper(User.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

}
