package ru.bogachev.jdbctemplate;

import ru.bogachev.dao.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Stanislav Bogachev
 * created on 07.04.2020
 */
public class JdbcTemplateDemo {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSourceH2();
        JdbcTemplateDemo demo = new JdbcTemplateDemo();

        demo.createTable(dataSource);

        JdbcOperations<User> jdbcUser = new JdbcTemplate<>(dataSource);
        jdbcUser.create(new User(1,"User1", 20));
    }

    private void createTable(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement(
                     "create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")) {
            pst.executeUpdate();
        }
        System.out.println("table created");
    }
}
