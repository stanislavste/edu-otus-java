package ru.bogachev.jdbctemplate;

import ru.bogachev.dao.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTemplateDemo {
    /*private static String URL = "jdbc:h2:mem:";
    private final Connection connection;

    public JdbcTemplateDemo() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }*/

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DataSourceH2();
        JdbcTemplateDemo demo = new JdbcTemplateDemo();

        demo.createTable(dataSource);

        JdbcOperations<User> jdbcUser = new JdbcTemplate<>(dataSource);
        jdbcUser.create(new User(0,"User0", 0));
        jdbcUser.create(new User(1,"User1", 1));


        //Optional<User> user = jdbcUser.getUser(0);

        /*System.out.println(user);
        user.ifPresentOrElse(crUser -> System.out.println("created user, name:" + crUser.getName()),
                () -> System.out.println("user was not created"));
*/

    }

    /*private void createTable() throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))"
        );
    }*/
    private void createTable(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement(
                     "create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")) {
            pst.executeUpdate();
        }
        System.out.println("table created");
    }
}
