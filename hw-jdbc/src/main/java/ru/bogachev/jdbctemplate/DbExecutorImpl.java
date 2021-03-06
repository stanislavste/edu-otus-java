package ru.bogachev.jdbctemplate;

import ru.bogachev.annotations.Id;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Stanislav Bogachev
 * created on 07.04.2020
 */
public class DbExecutorImpl<T> implements DbExecutor<T> {

    private final Connection connection;

    DbExecutorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insertRecord(String sql, List<T> params) throws SQLException, IllegalAccessException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (T param : params) {
                Class clazz = param.getClass();
                Field[] fields = clazz.getDeclaredFields();
                int index = 1;
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (!field.isAnnotationPresent(Id.class))
                        pst.setObject(index++, field.get(param));
                }
            }
            System.out.println(pst.toString());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            this.connection.rollback(savePoint);
            System.out.println(ex.getMessage());
            throw ex;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public Optional<T> selectRecord(String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException {
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.apply(rs));
            }
        }
    }
}
