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

    public Book createNewBook(BookRequest request) {
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

    public Book editBook(Long id, BookRequest request) {
        Book book = findById(id);

        Optional<Author> optionalAuthor = authorService.findByNameAndSurname(request.author.name, request.author.surname);
        Author author;
        if(optionalAuthor.isEmpty()){
            author = authorService.save(new Author(request.author.name, request.author.surname));
        }
        else {
            author = optionalAuthor.get();
        }
        return save(book);
    }

    public Book borrowBook(Long id) {
        Book book = findById(id);
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies()-1);
        }
        else
            throw new RuntimeException("No available copies");
        return book;
    }


    private Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }


}
