package ru.istomin.spring_hW3_GB.model;

import java.util.Objects;

public class Book {
    private Long id;
    private String name;

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getId() == book.getId() && Objects.equals(getName(), book.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}