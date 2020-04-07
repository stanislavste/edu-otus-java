package ru.bogachev.jdbctemplate;

import ru.bogachev.annotations.Id;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Collections;

public class JdbcTemplate<T> implements JdbcOperations<T> {
    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Object objectData) {
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean haveId = false;
        Field fieldWithId = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                haveId = true;
                fieldWithId = field;
            }
        }
        if (!haveId) {
            System.out.println("The class does not have a field with annotation @Id");
            return;
        }
        try (Connection connection = dataSource.getConnection()) {
            DbExecutor<Object> executor = new DbExecutorImpl<>(connection);
            for (Field field : fields
            ) {
                if (!field.equals(fieldWithId)) {
                    long objectId = executor.insertRecord(
                            "insert into user(name, age) values (?, ?)",
                            Collections.singletonList(objectData.getClass().getName()));
                    connection.commit();
                    System.out.println("created:" + objectId);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
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
