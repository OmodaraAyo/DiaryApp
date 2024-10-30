package ofofo.services;

import ofofo.data.models.Diary;
import ofofo.data.models.Entry;

public interface EntryServices {

    void save(String title, String body);
    Entry findByTitle(String title);
    void deleteById(String title);
    long count();
    Diary Id();

}
