package finki.emt.lab.onlinelibrary.controller;

import finki.emt.lab.onlinelibrary.model.Book;
import finki.emt.lab.onlinelibrary.model.request.BookRequest;
import finki.emt.lab.onlinelibrary.service.BookService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/game")
@CrossOrigin("http://localhost:4200/")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/all")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @PostMapping("/add")
    public Book createBook(@RequestBody BookRequest request) {
        return bookService.createNewBook(request);
    }

    @PutMapping("{id}/edit")
    public Book editBook(@PathVariable Long id, @RequestBody BookRequest request) {
        return bookService.editBook(id, request);
    }

    @DeleteMapping("{id}/delete")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
