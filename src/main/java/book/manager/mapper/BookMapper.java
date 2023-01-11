package book.manager.mapper;

import book.manager.entity.Book;
import book.manager.entity.Student;
import org.apache.ibatis.annotations.Insert;

public interface BookMapper {
    @Insert("insert into student(name,sex,grade) values(#{name},#{sex},#{grade})")
    int addStudent(Student student);

    //desc为关键字 添加esc下面的那个健
    @Insert("insert into book(title,`desc`,price) values(#{title},#{desc},#{price})")
    int addBook(Book book);
}
