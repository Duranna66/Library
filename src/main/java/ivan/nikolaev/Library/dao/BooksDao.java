package ivan.nikolaev.Library.dao;

import ivan.nikolaev.Library.connector.Jdbc;
import ivan.nikolaev.Library.model.Book;
import ivan.nikolaev.Library.model.Client;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BooksDao {
    JdbcTemplate jdbcTemplate = new Jdbc().jdbcTemplate();

    public List<Book> index() {
        return jdbcTemplate.query("Select title, author, year, id from Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book(person_id, title, author, year) values(0, ?, ?, ?)", book.getTitle(),
                book.getAuthor(), book.getYear());
    }

    public Book info(int id) {
        return jdbcTemplate.query("Select * from Book where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny()
                .orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("Delete from Book where id=?", id);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("Update Book set title=?, author=?, year=? where id =?", book.getTitle(),
                book.getAuthor(), book.getYear(), id);
    }

    public void receive(int id, Client client) {
        jdbcTemplate.update("Update Book set person_id=? where id=?", client.getId(), id);
    }

    public void bb(int id) {
        jdbcTemplate.update("UPDATE Book set person_id=? where id=?",-1, id);
    }

    public Optional<Client> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                        "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Client.class))
                .stream().findAny();
    }
}
