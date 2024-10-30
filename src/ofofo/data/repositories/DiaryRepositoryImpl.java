package ofofo.data.repositories;

import ofofo.data.models.Diary;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository {
    private boolean isEmpty = true;
    private long count = 0;
    private final List <Diary> diaries =  new ArrayList<>();

    @Override
    public void save(Diary diary) {
        diaries.add(diary);
        isEmpty = false;
        count++;
    }

//    @Override
//    public void update(Diary diary) {
//        Diary search = searchUser(diary.getUserName());
//        if (search != null) {
//            diary.setUserName(diary.getUserName());
//        }
//    }

    @Override
    public Diary findByName(String name) {
        Diary diary = searchUser(name);
        if(diary == null){
            throw new RuntimeException("UserName does not exist");
        }
        return diary;
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public void delete(Diary diary) {
        Diary search = searchUser(diary.getUserName());
        if (search == null) {
            throw new RuntimeException("UserName does not exist");
        }
        diaries.remove(search);
        count--;
        System.out.println(search+" \nSuccessfully Deleted.");
    }

    public long count() {
        return this.count;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Diary searchUser(String name) {
        for (Diary diary : diaries) {
            if(diary.getUserName().equalsIgnoreCase(name)) {
                return diary;
            }
        }
        return null;
    }
}
