package ru.istomin.spring_hW3_GB.repository;

import org.springframework.stereotype.Repository;
import ru.istomin.spring_hW3_GB.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/
@Repository
public class IssueRepository {
    private static AtomicLong count = new AtomicLong(0L);
    private final Map<Long, Issue> issues = new ConcurrentHashMap<>();


    public Issue getByID(Long id) {
        return issues.get(id);
    }

    public List<Issue> getAll() {
        return new ArrayList<>(issues.values());
    }

    public List<Issue> getIssuesByReader(Long readerId) {
        return issues.values()
                .stream()
                .filter(x -> Objects.equals(x.getReaderId(), readerId))
                .filter(i -> i.getReturnedAt() == null)
                .toList();
    }

    public Issue save(Issue issue) {
        issue.setId(count.incrementAndGet());
        issues.put(issue.getId(), issue);
        return issue;
    }

    public Issue update(Long id) {
        Issue updatedIssue = issues.get(id);
        if (updatedIssue != null) {
            updatedIssue.setReturnedAt(LocalDateTime.now());
        }
        return updatedIssue;
    }

    public void deleteById(Long id) {
        issues.remove(id);
    }
}