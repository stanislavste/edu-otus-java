package ru.bogachev.jdbctemplate;

import ru.bogachev.annotations.Id;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Collections;

/**
 * @author Stanislav Bogachev
 * created on 07.04.2020
 */
public class JdbcTemplate<T> implements JdbcOperations<T> {
    private final DataSource dataSource;

    JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(T objectData) {
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean haveId = false;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) haveId = true;
        }
        if (!haveId) {
            System.out.println("The class does not have a field with annotation @Id");
            return;
        }
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                try (Connection connection = dataSource.getConnection()) {
                    DbExecutor<Object> executor = new DbExecutorImpl<>(connection);
                    System.out.println(objectData);
                    System.out.println(clazz.getSimpleName());
                    long objectId = executor.insertRecord(
                            "insert into " + clazz.getSimpleName() + "(" + field.getName() + ") values (?)",
                            Collections.singletonList(field.get(objectData)));
                    connection.commit();
                    System.out.println("created field:" + objectId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void update(Object objectData) {

    }

    @Override
    public <T> T load(long id, Class<T> clazz) {
        return null;
    }
}
