package ru.istomin.spring_hW3_GB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.istomin.spring_hW3_GB.model.Book;
import ru.istomin.spring_hW3_GB.repository.BookRepository;

import java.util.List;

@Service //Аннотация @Service используется для сервисных компонентов, которые содержат бизнес-логику
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getByID(Long id) {
        return bookRepository.getByID(id);
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Long id, Book book) {
        return bookRepository.update(id, book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
