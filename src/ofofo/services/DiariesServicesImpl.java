package ofofo.services;


import ofofo.data.models.Diary;
import ofofo.data.repositories.DiaryRepositoryImpl;

public class DiariesServicesImpl implements DiariesService {
    private final DiaryRepositoryImpl repository;
    private long count = 0;

    public DiariesServicesImpl(DiaryRepositoryImpl diaryRepository) {
        this.repository = diaryRepository;
    }

    @Override
    public void register(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        String name = validateUserName(userName);
        repository.save(new Diary(name, password));
        count++;
        System.out.println("Diary registered successfully");
    }

    @Override
    public Diary findByUserName(String userName) {
        return repository.findByName(userName);
    }

    @Override
    public void deleteByUserName(String userName) {
        repository.delete(findByUserName(userName));
        count--;
    }

    public long count() {
        return count;
    }

    private void setUserName(String userName) {
        if(userName == null) {
            throw new NullPointerException("A userName is required");
        }
    }
    private void setPassword(String password) {
        if(password == null) {
            throw new NullPointerException("A password is required");
        }
    }

    private String validateUserName(String userName) {
        if(repository.searchUser(userName) != null) {
            throw new RuntimeException("UserName Already Exists");
        }
        return userName;
    }
}
