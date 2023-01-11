package book.manager;


import book.manager.entity.Book;
import book.manager.entity.Student;
import book.manager.sql.SqlUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

@Log
public class Main {
    public static void main(String[] args) throws IOException {
        try(Scanner scanner = new Scanner(System.in)){

            /*开启日志系统*/
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));

            while(true){
                System.out.println("*************");
                System.out.println("1.录入学生信息");
                System.out.println("2.录入书籍信息");
                System.out.println("3.添加借阅信息");
                System.out.println("4.查询借阅信息");
                System.out.println("3.添加借阅信息");
                System.out.println("4.查询借阅信息");
                System.out.println("5.查询学生信息");
                System.out.println("6.查询书籍信息");
                System.out.println("输入您想要执行的操作,按其他任意键退出");
                int input;
                try{
                    input = scanner.nextInt();
                }catch (Exception e){
                    return;
                }
                scanner.nextLine();
                switch (input){
                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        addBook(scanner);
                        break;
                    case 3:
                        addBorrow(scanner);
                        break;
                    case 4:
                        showBorrow();
                        break;
                    case 5:
                        showStudent();
                        break;
                    case 6:
                        showBook();
                        break;
                    default:
                        return;
                }
            }
        }
    }

    public static void showBook(){
        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.getBookList().forEach(System.out::println);
        });
    }


    public static void showStudent(){
        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.getStudentList().forEach(System.out::println);
        });
    }


    private static void showBorrow(){
        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.getBorrowList().forEach(borrow->{
                System.out.println(borrow.getStudent().getName()+"->"+borrow.getBook().getTitle());
            });
        });
    }







    /*添加借阅信息*/
    private static void addBorrow(Scanner scanner){
        System.out.println("请输入学生号");
        int sid = scanner.nextInt();
        scanner.nextLine();

        System.out.println("请输入书籍号");
        int bid = scanner.nextInt();
        scanner.nextLine();

        SqlUtil.doSqlWork(bookMapper -> {
            bookMapper.addBorrow(sid,bid);
        });

    }

    /*添加书籍信息*/
    private static void addBook(Scanner scanner){
        System.out.println("请输入书籍标题:");
        String title = scanner.nextLine();
        System.out.println("请输入书籍介绍");
        String desc = scanner.nextLine();
        System.out.println("请输入书籍价格");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Book b = new Book(title,desc,price);
        SqlUtil.doSqlWork(bookMapper -> {
            int i = bookMapper.addBook(b);
            if(i > 0) {
                System.out.println("书籍信息录入成功!");
                log.info("新添加一条书籍信息"+b);
            }else System.out.println("书籍信息录入失败,请重试");
        });
    }

    /*添加学生信息*/
    private static void addStudent(Scanner scanner){
        System.out.println("请输入学生名字");
        String name = scanner.nextLine();
        System.out.println("请输入学生性别");
        String sex = scanner.nextLine();
        System.out.println("请输入学生年级");
        int grade = scanner.nextInt();
        scanner.nextLine();
        Student s = new Student(name,sex,grade);
        SqlUtil.doSqlWork(bookMapper -> {
            int i = bookMapper.addStudent(s);
            if(i > 0) {
                System.out.println("学生信息录入成功!");
                log.info("新添加一条学生信息:"+s);
            }else System.out.println("学生信息录入失败,请重试");

        });
    }
}
