package ru.istomin.spring_hW3_GB.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.istomin.spring_hW3_GB.model.Issue;
import ru.istomin.spring_hW3_GB.service.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getByID(@PathVariable("id") Long id) {
        Issue issue = issueService.getByID(id);
        if (issue != null) {
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAll() {
        return ResponseEntity.ok(issueService.getAll());
    }

    @PostMapping
    public ResponseEntity<Issue> create(@RequestBody Issue issue) {
        if (issue != null) {
            try {
                issue = issueService.saveIssue(issue);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (RuntimeException ex) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(issue, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> update(@PathVariable("id") Long id) {
        Issue updatedIssue = issueService.update(id);
        if (updatedIssue != null) {
            return new ResponseEntity<>(updatedIssue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        issueService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
