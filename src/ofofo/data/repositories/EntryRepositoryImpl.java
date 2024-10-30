package ofofo.data.repositories;

import ofofo.data.models.Entry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository {
    private boolean isEmpty = true;
    private long count = 0;
    private final List<Entry> entries = new ArrayList<>();

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public void save(Entry entry) {
        Entry checkSavedEntry = searchEntries(entry.getTitle());
        if (checkSavedEntry != null) {
            updateContent(entry);
        }
        else {
            entries.add(entry);
            isEmpty = false;
            count++;
        }
    }

    @Override
    public void updateContent(Entry entry) {
        Entry searchedEntry = searchEntries(entry.getTitle());
        if (searchedEntry != null) {
            Entry newEntry = new Entry(entry.getTitle(), entry.getContent());
            entries.set(entries.indexOf(searchedEntry), newEntry);
        }
    }

    @Override
    public void delete(Entry entry) {
        Entry searchedEntry = searchEntries(entry.getTitle());
        if (searchedEntry != null) {
            entries.remove(searchedEntry);
            count--;
        }
    }

    @Override
    public Entry findByTitle(String title) {
        return searchEntries(title);
    }

    @Override
    public List<Entry> findAll() {
        return new ArrayList<>(entries);
    }

    @Override
    public long count() {
        return count;
    }

    public Entry searchEntries(String title) {
        for (Entry entry : entries) {
            if(entry.getTitle().equalsIgnoreCase(title)) {
                return entry;
            }
        }
        return null;
    }
}
