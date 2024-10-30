package ofofo.data.repositories;

import ofofo.data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EntryRepositoryImplTest {
    private EntryRepository repo;
    @BeforeEach
    public void startAllWithThis() {
        repo = new EntryRepositoryImpl();
    }

    @Test
    public void That_EntriesRepository_Is_Empty() {
        assertTrue(repo.isEmpty());
    }

    @Test
    public void when_Entries_are_Saved_To_The_EntryRepository_Test_That_It_Is_No_Longer_Empty(){
        assertTrue(repo.isEmpty());
        Entry entry = new Entry("Hello", "World");
        repo.save(entry);
        assertFalse(repo.isEmpty());
    }

    @Test
    public void when_Multiple_Entries_Are_Saved_Test_To_Find_Saved_Entry_By_Title(){
        assertEquals(0, repo.count());
        Entry entry = new Entry("Hello", "World");
        repo.save(entry);
        assertEquals(1, repo.count());
        Entry gameEntry = new Entry("Games", "Fifa19");
        repo.save(gameEntry);
        assertEquals(2, repo.count());
        assertEquals("Games", repo.findByTitle("Games").getTitle());
    }

    @Test
    public void test_To_Delete_A_Entry_That_Already_Exist(){
        assertEquals(0, repo.count());
        Entry entry = new Entry("Hello", "World");
        repo.save(entry);
        assertEquals(1, repo.count());
        Entry gameEntry = new Entry("Games", "Fifa19");
        repo.save(gameEntry);
        assertEquals(2, repo.count());
        repo.delete(entry);
        assertEquals(1, repo.count());
    }

    @Test
    public void test_To_Update_An_Entry_That_Already_Exist(){
        assertEquals(0, repo.count());
        Entry entry = new Entry("Hello", "World");
        repo.save(entry);
        assertEquals(1, repo.count());
        Entry gameEntry = new Entry("Games", "Fifa19");
        repo.save(gameEntry);
        assertEquals(2, repo.count());
        assertEquals("Fifa19", repo.findByTitle("Games").getContent());
        Entry updatedEntry = new Entry("Games", "GodOfWar");
        repo.updateContent(updatedEntry);
        assertEquals("GodOfWar", repo.findByTitle("Games").getContent());
        assertEquals(2, repo.count());
        Entry gameEntry2 = new Entry("Games", "Rumble Racing");
        repo.save(gameEntry2);
        assertEquals(2, repo.count());
    }

    @Test
    public void test_To_FindAll_Saved_Entries(){
        assertEquals(0, repo.count());
        Entry entry = new Entry("Movies", "World");
        repo.save(entry);
        assertEquals(1, repo.count());
        Entry gameEntry = new Entry("Games", "Fifa19");
        repo.save(gameEntry);
        assertEquals(2, repo.count());
        List<Entry> allEntries = repo.findAll();
        assertEquals(2, allEntries.size());
        assertEquals("Movies", allEntries.get(0).getTitle());
        assertEquals("Games", allEntries.get(1).getTitle());
    }
}
