package ru.istomin.spring_hW3_GB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.istomin.spring_hW3_GB.model.Issue;
import ru.istomin.spring_hW3_GB.model.Reader;
import ru.istomin.spring_hW3_GB.repository.IssueRepository;
import ru.istomin.spring_hW3_GB.repository.ReaderRepository;

import java.util.List;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository, IssueRepository issueRepository) {
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }

    public Reader getByID(Long id) {
        return readerRepository.getByID(id);
    }

    public List<Reader> getAll() {
        return readerRepository.getAll();
    }

    // Для получения списка выдач книг читателю используем метод, реализованный в репозитории выдач книг
    public List<Issue> getIssues(Long readerId) {
        return issueRepository.getIssuesByReader(readerId);
    }

    public Reader create(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader update(Long id, Reader reader) {
        return readerRepository.update(id, reader);
    }

    public void deleteById(Long id) {
        readerRepository.deleteById(id);
    }
}
