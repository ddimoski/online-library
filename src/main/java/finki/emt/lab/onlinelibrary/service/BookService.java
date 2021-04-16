package finki.emt.lab.onlinelibrary.service;

import finki.emt.lab.onlinelibrary.model.Author;
import finki.emt.lab.onlinelibrary.model.Book;
import finki.emt.lab.onlinelibrary.model.enums.CategoryEnum;
import finki.emt.lab.onlinelibrary.model.request.BookRequest;
import finki.emt.lab.onlinelibrary.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CountryService countryService;

    public BookService(BookRepository bookRepository, AuthorService authorService,
                       CountryService countryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.countryService = countryService;
    }


    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
            new RuntimeException(String.format("No book with id %d found", id)));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    private Book createNewBook(BookRequest request) {
        Optional<Author> optionalAuthor = authorService.findByNameAndSurname(request.author.name, request.author.surname);
        Author author;
        if(optionalAuthor.isEmpty()){
            author = authorService.save(new Author(request.author.name, request.author.surname));
        }
        else {
            author = optionalAuthor.get();
        }
        Book book = new Book(request.name, CategoryEnum.valueOf(request.category), author);
        book.setAvailableCopies(0);
        return save(book);
    }

    private Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }


}
