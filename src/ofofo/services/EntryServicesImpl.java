package ofofo.services;

import ofofo.data.models.Diary;
import ofofo.data.models.Entry;
import ofofo.data.repositories.EntryRepository;
import ofofo.data.repositories.EntryRepositoryImpl;

public class EntryServicesImpl implements EntryServices {
    private final EntryRepository entryRepository;
//    private long count = 0;

    public EntryServicesImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public void save(String title, String body) {
        entryRepository.save(new Entry(title, body));
//        count++;
        System.out.println("Saved successfully");
    }

    @Override
    public Entry findByTitle(String title) {
        if(entryRepository.findByTitle(title) == null){
            throw new RuntimeException("No entry found with title: " + title);
        }
        return entryRepository.findByTitle(title);
    }

    @Override
    public void deleteById(String title) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Diary Id() {
        return null;
    }
}
