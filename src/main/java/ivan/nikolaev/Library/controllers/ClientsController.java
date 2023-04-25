package ivan.nikolaev.Library.controllers;

import ivan.nikolaev.Library.dao.ClientsDao;
import ivan.nikolaev.Library.model.Book;
import ivan.nikolaev.Library.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class ClientsController {
    private ClientsDao dao;
    @Autowired

    public ClientsController(ClientsDao dao) {
        this.dao = dao;
    }
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("clients", dao.index());
        return "clients/show";
    }
    @PostMapping()
    public String create(@ModelAttribute("client") Client client)
   {
        dao.create(client);
        return "redirect:/people";
   }
    @GetMapping("/new")
    public String edit(@ModelAttribute("client") Client client)
   {

        return "clients/new";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("person", dao.show(id));
        model.addAttribute("books", dao.getBooks(id));
        return "clients/info";
    }
    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        dao.delete(id);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("client", dao.show(id));
        return "clients/edit";
    }
    @PostMapping("/{id}/change")
    public String change(@PathVariable("id") int id, @ModelAttribute("client") Client client)
    {
        dao.update(client, id);
        return "redirect:/people";
    }
}
