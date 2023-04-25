package ivan.nikolaev.Library.connector;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Jdbc {
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/project1");
        dataSource.setUsername("root");
        dataSource.setPassword("randomPassword"); //don't forget to change the password)
        return dataSource;
    }

    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    private JdbcTemplate jdbcTemplate = jdbcTemplate();
}
