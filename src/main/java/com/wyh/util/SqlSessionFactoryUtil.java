package com.wyh.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
/*
* MyBatis的获取SqlSessionFactory对象的重复操作
* 抽取出来创建为一个工具类 且为static静态加载
* 即可只需要创建一次这个对象*/
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSessionFactory = factory;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
