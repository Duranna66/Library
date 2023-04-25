package ivan.nikolaev.Library.dao;


import ivan.nikolaev.Library.connector.Jdbc;
import ivan.nikolaev.Library.model.Book;
import ivan.nikolaev.Library.model.Client;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class ClientsDao {
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/project1");
//        dataSource.setUsername("root");
//        dataSource.setPassword("61neMVWH220");
//        return dataSource;
//    }
//
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource());
//    }

    private JdbcTemplate jdbcTemplate = new Jdbc().jdbcTemplate();


    public List<Client> index()
    {
        return jdbcTemplate.query("Select * from Person",new BeanPropertyRowMapper<>(Client.class));
    }

    public void create(Client client) {
        jdbcTemplate.update("INSERT INTO Person(FIO, yearOfBorn) VALUES(?, ?)", client.getFIO(), client.getYearOfBorn());
    }
    public Client show(int id)
    {
        return jdbcTemplate.query("Select * from Person where id = ?", new Object[]{id},new BeanPropertyRowMapper<>(Client.class))
                .stream().findAny().orElse(null);
    }
    public void delete(int id)
    {
        jdbcTemplate.update("Delete from Person where id=?", id);
    }
    public void update(Client client, int id)
    {
        jdbcTemplate.update("Update Person set FIO=?, yearOfBorn=? where id=?", client.getFIO(), client.getYearOfBorn(), id);
    }
    public List<Book> getBooks(int id)
    {
        return jdbcTemplate.query("Select * from Book where person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
