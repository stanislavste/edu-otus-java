package ru.bogachev.jdbctemplate;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbExecutorImpl<T> implements DbExecutor<T> {

    private final Connection connection;

    public DbExecutorImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insertRecord(String sql, List<T> params) throws SQLException, IllegalAccessException {
        Savepoint savePoint = this.connection.setSavepoint("savePointName");
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int idx = 0; idx < params.size(); idx++) {
                System.out.println(params); //распарсить парамс вытащить вэлью для всех
                Class clazz = params.get(idx).getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().equals("name")) {
                        field.setAccessible(true);
                        pst.setString(idx + 1, (String) field.get(params.get(idx)));
                    }
                    if (field.getName().equals("age")) {
                        field.setAccessible(true);
                        pst.setInt(idx + 2, field.getInt(params.get(idx)));
                    }
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
