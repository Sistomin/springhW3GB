package ru.istomin.spring_hW3_GB.model;

import java.util.Objects;

public class Reader {
    private Long id;
    private String name;

    public Reader(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Reader(String name) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reader reader)) return false;
        return getId() == reader.getId() && Objects.equals(getName(), reader.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}