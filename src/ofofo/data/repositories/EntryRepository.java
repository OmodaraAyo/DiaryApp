package ofofo.data.repositories;

import ofofo.data.models.Entry;

import java.util.List;

public interface EntryRepository {
    boolean isEmpty();
    void save(Entry entry);
    void updateContent(Entry entry);
    void delete(Entry entry);
    Entry findByTitle(String title);
    List<Entry> findAll();
    long count();
}
