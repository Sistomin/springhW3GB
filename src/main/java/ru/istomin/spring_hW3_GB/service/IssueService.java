package ru.istomin.spring_hW3_GB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.istomin.spring_hW3_GB.model.Issue;
import ru.istomin.spring_hW3_GB.repository.BookRepository;
import ru.istomin.spring_hW3_GB.repository.IssueRepository;
import ru.istomin.spring_hW3_GB.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IssueService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    @Value("${application.max-allowed-books:1}") //аннотация которая указывает значение по умолчанию для аргумента
    private int maxAllowedBooks;

    @Autowired //аннотация для конструкторов
    public IssueService(BookRepository bookRepository, ReaderRepository readerRepository,
                        IssueRepository issueRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }

    public Issue getByID(Long id) {
        return issueRepository.getByID(id);
    }

    public List<Issue> getAll() {
        return issueRepository.getAll();
    }

    public Issue saveIssue(Issue issue) {
        if (bookRepository.getByID(issue.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с указанным ID \"" + issue.getBookId() + "\"");
        }
        if (readerRepository.getByID(issue.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с указанным ID \"" + issue.getReaderId() + "\"");
        }
        if (maxAllowedBooks <= issueRepository.getIssuesByReader(issue.getReaderId()).size()) {
            throw new RuntimeException("Читателю \"" + issue.getReaderId() + "\" отказано в выдаче  книг по причине " +
                    "превышено кол-во книг у читателя. Взятые книги ранее необходимо вернуть.");
        }
        return issueRepository.save(issue);
    }

    public Issue update(Long id) {
        return issueRepository.update(id);
    }

    public void deleteById(Long id) {
        issueRepository.deleteById(id);
    }
}
