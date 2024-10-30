package ofofo.data.model;

import ofofo.data.models.Diary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest {


    @Test
    public void test_That_Diary_Is_Unlock_By_Default() {
        Diary diary = new Diary();
        assertFalse(diary.isLocked());
    }

    @Test
    public void testToCreateDiary_For_AUser_Without_A_Password(){
        Diary diary = new Diary("Pablo");
        assertFalse(diary.isLocked());
        assertEquals("Pablo", diary.getUserName());
        assertNull(diary.getPassword());
        diary.lockDiary();
        assertTrue(diary.isLocked());
        diary.unlockDiary();
        assertFalse(diary.isLocked());
        diary.setPassword("GodOfWar123");
        diary.lockDiary();
        assertTrue(diary.isLocked());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            diary.unlockDiary();
        });
        assertEquals("Provide password to unlock the diary", exception.getMessage());
        assertTrue(diary.isLocked());
        diary.unlockDiary("GodOfWar123");
        assertFalse(diary.isLocked());
    }

    @Test
    public void testToCreateDiary_For_AUser_With_A_Password(){
        Diary franklinDiary = new Diary("Franklin", "Yoyo12345");
        assertEquals("Franklin", franklinDiary.getUserName());
        franklinDiary.lockDiary();
        assertTrue(franklinDiary.isLocked());
        RuntimeException exception = assertThrows(RuntimeException.class, franklinDiary::unlockDiary);
        assertEquals("Provide password to unlock the diary", exception.getMessage());
        assertTrue(franklinDiary.isLocked());
        franklinDiary.unlockDiary("Yoyo12345");
        assertFalse(franklinDiary.isLocked());
    }
}
