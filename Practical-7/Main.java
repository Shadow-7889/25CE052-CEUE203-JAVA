import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {
    @Column(name = "StudentName")
    private String name;

    @Column(name = "StudentAge")
    private int age;

    public String toString() {
        return name + " " + age;
    }
}

class RowMapper {
    public static <T> T populate(
            T obj, String[] headers, String[] data) throws Exception {

        for (Field field : obj.getClass().getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);

            if (column == null)
                continue;

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(column.name())) {
                    field.setAccessible(true);

                    if (field.getType() == String.class)
                        field.set(obj, data[i]);
                    else if (field.getType() == int.class)
                        field.setInt(obj, Integer.parseInt(data[i]));

                    break;
                }
            }
        }
        return obj;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        String[] headers = {"StudentName", "StudentAge"};
        String[] data = {"Mann", "20"};

        Student s = RowMapper.populate(new Student(), headers, data);
        System.out.println(s);

        System.out.println("25CE052-Mann");
    }
}