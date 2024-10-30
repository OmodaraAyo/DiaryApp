package ofofo.data.repositories;

import ofofo.data.models.Diary;

import java.util.List;

public interface DiaryRepository {
    boolean isEmpty();
    void save(Diary diary);
//    void update(Diary diary);
    void delete(Diary diary);
    Diary findByName(String name);
    List<Diary> findAll();
    long count();
}
