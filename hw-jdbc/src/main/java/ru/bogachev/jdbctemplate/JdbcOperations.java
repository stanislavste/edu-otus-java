package ru.bogachev.jdbctemplate;

public interface JdbcOperations<T> {
    void create(T objectData);
    void update(T objectData);
    <T> T load(long id, Class<T> clazz);
}
