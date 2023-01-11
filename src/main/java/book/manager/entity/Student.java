package book.manager.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
//@Accessors(chain = true)
public class Student {
    int sid;
    final String name;
    final String sex;
    final int grade;
}
