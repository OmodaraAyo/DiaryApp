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
        String expected = "The Subtle Art Of Not Giving A Fuck";
        assertEquals(expected, entryServices.findByTitle("Books I need To Read").getContent());
    }

    @Test
    public void test_That_An_Exception_Is_Thrown_When_An_Entry_That_Does_Not_Exist_Is_Searched(){
        entryServices.save("Story Of My Life", "First Day I Met My Wife");
        assertEquals(1, entryRepository.count());
        entryServices.save("Books I need To Read", "The Subtle Art Of Not Giving A Fuck");
        assertEquals(2, entryRepository.count());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
           entryServices.findByTitle("My GirlFriends");
        });
        assertEquals("No entry found with title: My GirlFriends", exception.getMessage());
    }

    @Test
    public void test_To_Delete_An_Entry_In_The_EntryRepository(){

    }
}
