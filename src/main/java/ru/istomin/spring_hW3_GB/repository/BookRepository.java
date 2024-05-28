package ru.istomin.spring_hW3_GB.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.istomin.spring_hW3_GB.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class BookRepository {
    private static AtomicLong count = new AtomicLong(0L);
    private final Map<Long, Book> books = new ConcurrentHashMap<>();

    /**
     * Аннотация @PostConstruct используется для обозначения метода, который должен быть выполнен сразу после
     * создания объекта и завершения работы конструктора, но до того, как объект будет использован.
     * Важно понимать, что этот метод выполняется только один раз во время жизненного цикла объекта.
     * Наполняем хранилище книгами
     */
    @PostConstruct
    private void generateBooks() {
        books.put(count.incrementAndGet(),
                new Book(count.get(), "Азбука"));
        books.put(count.incrementAndGet(),
                new Book(count.get(), "Алгебра"));
        books.put(count.incrementAndGet(),
                new Book(count.get(), "Война и мир"));
        books.put(count.incrementAndGet(),
                new Book(count.get(), "Незнайка на луне"));
        books.put(count.incrementAndGet(),
                new Book(count.get(), "«12 стульев"));
    }

    public Book getByID(Long id) {
        return books.get(id);
    }

    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    public Book save(Book book) {
        book.setId(count.incrementAndGet());
        books.put(book.getId(), book);
        return book;
    }

    public Book update(Long id, Book book) {
        Book updatedBook = books.get(id);
        if (updatedBook != null) {
            updatedBook.setName(book.getName());
        }
        return updatedBook;
    }

    public void deleteById(Long id) {
        books.remove(id);
    }
}
