package book.manager;


import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try(Scanner scanner = new Scanner(System.in)){
            while(true){
                System.out.println("*************");
                System.out.println("1.录入学生信息");
                System.out.println("2.录入书籍信息");
                System.out.println("输入您想要执行的操作,按其他任意键退出");
                int input;
                try{
                    input = scanner.nextInt();
                }catch (Exception e){
                    return;
                }

                switch (input){
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        return;
                }
            }
        }
    }
}
