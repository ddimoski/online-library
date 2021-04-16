package finki.emt.lab.onlinelibrary.service;

import finki.emt.lab.onlinelibrary.model.Author;
import finki.emt.lab.onlinelibrary.repository.AuthorRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
            new RuntimeException(String.format("No author with id %d found", id)));
    }

    public Optional<Author> findByNameAndSurname(String name, String surname) {
        return authorRepository.findByNameAndSurname(name, surname);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

}
