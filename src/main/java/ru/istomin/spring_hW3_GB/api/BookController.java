package ru.istomin.spring_hW3_GB.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.istomin.spring_hW3_GB.model.Book;
import ru.istomin.spring_hW3_GB.service.BookService;

import java.util.List;

@RestController //аннотации для выражения отображений запросов, ввода запросов, обработки исключений и многого другого
@RequestMapping("/book") //Аннотация используется для отображения запросов на методы контроллеров
public class BookController {
    private final BookService bookService;

    @Autowired //аннотация для конструкторов
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}") //специализированная аннотация @RequestMapping, предусмотренная конкретно для обработки HTTP GET-запросов
    public ResponseEntity<Book> getByID(@PathVariable Long id) { //Аннотация @PathVariable используется для связывания значений из URL с параметрами метода
        Book book = bookService.getByID(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping //аннотация для создания новых данных
    public ResponseEntity<Book> create(@RequestBody Book book) {
        if (book != null) {
            return new ResponseEntity<>(bookService.create(book), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}") //аннотация для изменения данных
    public ResponseEntity<Book> update(@PathVariable("id") Long id, @RequestBody Book book) {
        if (book != null) {
            Book updatedBook = bookService.update(id, book);
            if (updatedBook != null) {
                return new ResponseEntity<>(updatedBook, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") //аннотация для удаления данных
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
