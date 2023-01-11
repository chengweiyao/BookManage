package com.test;

import book.manager.sql.SqlUtil;
import org.junit.jupiter.api.Test;

public class MainTest {
   @Test
    public void test1(){
       SqlUtil.doSqlWork(bookMapper -> {
           bookMapper.getBorrowList().forEach(System.out::println);
       });
   }


    @Test
    public void test2(){
        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.getStudentList().forEach(System.out::println);
        });
    }

    @Test
    public void test3(){
        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.getBookList().forEach(System.out::println);
        });
    }
}
