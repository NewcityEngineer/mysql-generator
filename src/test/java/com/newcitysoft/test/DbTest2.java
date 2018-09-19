package com.newcitysoft.test;


import com.newcitysoft.generator.dbtool.core.DefaultDataSourceExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/19 14:18
 */
public class DbTest2 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/springboot_demo?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String password = "root";

        DefaultDataSourceExecutor executor = new DefaultDataSourceExecutor(url, user, password);

        List<Dog> list = new ArrayList<>();

        Dog dog = new Dog();

        dog.setName("aa");
        dog.setWeight(12.56);
        dog.setAge(6);

        list.add(dog);

        int i = executor.batchInsert(Dog.class, list);

        System.out.println(i);
    }
}