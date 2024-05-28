package ru.istomin.spring_hW3_GB.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Issue {
    private Long id;
    private final Long bookId;
    private final Long readerId;
    private final LocalDateTime issuedAt;
    private LocalDateTime returnedAt = null;

    public Issue(Long bookId, Long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.issuedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue issue)) return false;
        return Objects.equals(getId(), issue.getId()) && Objects.equals(getBookId(), issue.getBookId()) && Objects.equals(getReaderId(), issue.getReaderId()) && Objects.equals(getIssuedAt(), issue.getIssuedAt()) && Objects.equals(getReturnedAt(), issue.getReturnedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookId(), getReaderId(), getIssuedAt(), getReturnedAt());
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", issuedAt=" + issuedAt +
                ", returnedAt=" + returnedAt +
                '}';
    }
}