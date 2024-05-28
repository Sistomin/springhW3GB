package ru.istomin.spring_hW3_GB.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.istomin.spring_hW3_GB.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReaderRepository {
    private static AtomicLong count = new AtomicLong(0L);
    private final Map<Long, Reader> readers = new ConcurrentHashMap<>();

    @PostConstruct
    private void generateBooks() {
        readers.put(count.incrementAndGet(),
                new Reader(count.get(), "Ivan"));
        readers.put(count.incrementAndGet(),
                new Reader(count.get(), "Anna"));
        readers.put(count.incrementAndGet(),
                new Reader(count.get(), "Oleg"));
        readers.put(count.incrementAndGet(),
                new Reader(count.get(), "Tom"));
        readers.put(count.incrementAndGet(),
                new Reader(count.get(), "Robert"));
    }

    public Reader getByID(Long id) {
        return readers.get(id);
    }

    public List<Reader> getAll() {
        return new ArrayList<>(readers.values());
    }

    public Reader save(Reader reader) {
        reader.setId(count.incrementAndGet());
        readers.put(reader.getId(), reader);
        return reader;
    }

    public Reader update(Long id, Reader reader) {
        Reader updatedReader = readers.get(id);
        if (updatedReader != null) {
            updatedReader.setName(reader.getName());
        }
        return updatedReader;
    }

    public void deleteById(Long id) {
        readers.remove(id);
    }
}
