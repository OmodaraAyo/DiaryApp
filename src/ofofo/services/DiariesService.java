package ofofo.services;

import ofofo.data.models.Diary;

public interface DiariesService {

    void register(String userName, String password);
    Diary findByUserName(String userName);
    void deleteByUserName(String userName);
    long count();
}
