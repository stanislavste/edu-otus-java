package ru.bogachev.jdbctemplate;

/**
 * @author Stanislav Bogachev
 * created on 07.04.2020
 */
public interface JdbcOperations<T> {
    void create(T objectData);
    void update(T objectData);
    <T> T load(long id, Class<T> clazz);
}
