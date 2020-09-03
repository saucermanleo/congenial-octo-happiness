package org.zy.orm;

import lombok.Data;

import java.util.Date;

/**
 * @author : 生态环境-张阳
 * @date : 2020/9/3 0003 11:33
 */
@Data
public class User {

    @Column("name")
    private String name;
    @Column("age")
    private int age;
    @Column("birthday")
    private Date birthday;


}
