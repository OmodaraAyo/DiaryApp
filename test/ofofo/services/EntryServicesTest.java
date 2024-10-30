package ofofo.services;

import ofofo.data.repositories.EntryRepository;
import ofofo.data.repositories.EntryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryServicesTest {
    EntryServices entryServices;
    EntryRepository entryRepository;

    @BeforeEach
    public void startAllWithThis() {
        entryRepository = new EntryRepositoryImpl();
        entryServices = new EntryServicesImpl(entryRepository);
    }

    @Test
    public void test_that_EntryRepository_Can_Save_new_Entry_Created_In_The_EntryService(){
        entryServices.save("Story Of My Life", "First Day I Met My Wife");
        assertEquals(1, entryRepository.count());
    }

    @Test
    public void when_An_Entry_Is_Saved_Test_To_Find_An_Entry_From_The_Saved_EntryRepository(){
        entryServices.save("Story Of My Life", "First Day I Met My Wife");
        assertEquals(1, entryRepository.count());
        entryServices.save("Books I need To Read", "The Subtle Art Of Not Giving A Fuck");
        assertEquals(2, entryRepository.count());
    }
}
