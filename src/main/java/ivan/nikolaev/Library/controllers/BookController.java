package ivan.nikolaev.Library.controllers;

import ivan.nikolaev.Library.dao.BooksDao;
import ivan.nikolaev.Library.dao.ClientsDao;
import ivan.nikolaev.Library.model.Book;
import ivan.nikolaev.Library.model.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
  private BooksDao dao;
  private ClientsDao clientsDao;
  @Autowired

    public BookController(BooksDao dao, ClientsDao clientsDao) {
        this.dao = dao;
      this.clientsDao = clientsDao;
  }
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("books", dao.index());
        return "books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book)
    {
        return "books/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "redirect:/books/new";
        }
        dao.create(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String info(@PathVariable("id") int id, Model model, @ModelAttribute("client")Client client)
    {
        model.addAttribute("book", dao.info(id));
        Optional<Client> clients = dao.getBookOwner(id);
        if(clients.isPresent())
        {
            model.addAttribute("owner", clients.get());
        }
        else
        {
            model.addAttribute("people", clientsDao.index());
        }
        return "books/info";
    }
    @PostMapping("/{id}")
    public String delete (@PathVariable("id") int id)
    {
        dao.delete(id);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, @ModelAttribute("book") Book book, Model model)
    {
        model.addAttribute("book", dao.info(id));
        return "books/edit";
    }
    @PostMapping("/{id}/up")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book)
    {
        dao.update(id, book);
        return "redirect:/books";
    }
    @PostMapping("/bb/{id}")
    public String bb(@PathVariable("id") int id)
    {
        dao.bb(id);
        return "redirect:/books";
    }
    @PostMapping("/receive/{id}")
    public String receive(@ModelAttribute("person") Client client, @PathVariable("id") int id)
    {
        dao.receive(id,client);
        return "redirect:/books";
    }
}

