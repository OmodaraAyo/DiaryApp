package ofofo.data.repositories;

import ofofo.data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DiaryRepositoryImplTest {
    private DiaryRepository diaryRepository;

    @BeforeEach
    public void startAllWithThis() {
        diaryRepository = new DiaryRepositoryImpl();
    }

    @Test
    public void That_DiariesRepository_Is_Empty() {
        assertTrue(diaryRepository.isEmpty());
    }

    @Test
    public void  when_A_Diary_Is_Saved_To_The_DiariesRepository_Test_That_It_Is_No_Longer_Empty(){
        Diary diary = new Diary("Franklin", "dev12345");
        diaryRepository.save(diary);
        assertFalse(diaryRepository.isEmpty());
    }

    @Test
    public void when_Multiple_Diaries_Are_Saved_Test_To_Find_Saved_Diary_By_Name(){
        assertEquals(0, diaryRepository.count());
        Diary franklinDiary = new Diary("Franklin", "dev12345");
        diaryRepository.save(franklinDiary);
        assertEquals(1, diaryRepository.count());
        Diary skullyDiary = new Diary("Skully", "divine");
        diaryRepository.save(skullyDiary);
        assertEquals(2, diaryRepository.count());
        assertEquals("Skully", diaryRepository.findByName("Skully").getUserName());
    }

    @Test
    public void test_To_Find_A_Non_Existing_Diary_Will_Throw_An_Exception(){
        RuntimeException runtimeException = assertThrows(RuntimeException.class, ()->{
            diaryRepository.findByName("ManBoy");
        });
        assertEquals("UserName does not exist", runtimeException.getMessage());
    }

    @Test
    public void test_TO_Delete_A_Diary_That_Already_Exist(){
        assertEquals(0, diaryRepository.count());
        Diary lyonDiary = new Diary("Lyon", "dev12345");
        diaryRepository.save(lyonDiary);
        assertEquals(1, diaryRepository.count());
        Diary fatBackDiary = new Diary("FatBack", "divine");
        diaryRepository.save(fatBackDiary);
        assertEquals(2, diaryRepository.count());
        diaryRepository.delete(lyonDiary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void test_To_Update_An_Entry_That_Already_Exist(){
        assertEquals(0, diaryRepository.count());
        Diary franklinDiary = new Diary("Franklin", "dev12345");
        diaryRepository.save(franklinDiary);
        assertEquals(1, diaryRepository.count());
        Diary skullyDiary = new Diary("Skully", "divine");
        diaryRepository.save(skullyDiary);
        assertEquals(2, diaryRepository.count());
    }
}
