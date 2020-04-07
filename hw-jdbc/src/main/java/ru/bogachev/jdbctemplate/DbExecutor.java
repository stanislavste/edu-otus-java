package ru.bogachev.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Stanislav Bogachev
 * created on 07.04.2020
 */
public interface DbExecutor<T> {

    long insertRecord(String sql, List<T> params) throws SQLException, IllegalAccessException;
    Optional<T> selectRecord(String sql, long id, Function<ResultSet, T> rsHandler) throws SQLException;
}
