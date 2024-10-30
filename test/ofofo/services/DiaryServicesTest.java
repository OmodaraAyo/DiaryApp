package ofofo.services;

import ofofo.data.repositories.DiaryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiaryServicesTest {
    private DiariesService diariesService;

    @BeforeEach
    public void startAllWithThis() {
        DiaryRepositoryImpl repository = new DiaryRepositoryImpl();
        diariesService = new DiariesServicesImpl(repository);
    }

    @Test
    public void test_To_Register_A_User_To_The_DiaryRepository() {
        diariesService.register("RagNak", "ivar1234");
        assertEquals("RagNak", diariesService.findByUserName("RagNak").getUserName());
        assertEquals(1, diariesService.count());
    }

    @Test
    public void test_That_Registering_A_User_With_A_Null_Name_To_The_DiaryRepository_Throws_An_Exception() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            diariesService.register(null, "ivar1234");
        });
        assertEquals("A userName is required", exception.getMessage());
        assertEquals(0, diariesService.count());
    }

    @Test
    public void test_That_Registering_A_User_With_A_Null_Password_To_The_DiaryRepository_Throws_An_Exception() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            diariesService.register("Ivar", null);
        });
        assertEquals("A password is required", exception.getMessage());
        assertEquals(0, diariesService.count());
    }

    @Test
    public void test_That_An_Exception_Is_Thrown_When_An_Already_Existing_User_Name_Is_Register_Again(){
        assertEquals(0, diariesService.count());
        diariesService.register("MorningStar", "ivar1234");
        assertEquals(1, diariesService.count());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            diariesService.register("MorningStar", "ivar1234");
        });
        assertEquals("UserName Already Exists", exception.getMessage());
        assertEquals(1, diariesService.count());
        diariesService.register("Tommy", "ivar1234");
        assertEquals(2, diariesService.count());
    }

    @Test
    public void if_users_has_been_registered_Test_To_Find_A_RegisteredUser_In_The_DiariesRepository(){
        diariesService.register("Messi", "i90290sk4");
        assertEquals(1, diariesService.count());
        assertEquals("Messi", diariesService.findByUserName("Messi").getUserName());
    }

    @Test
    public void test_That_An_Exception_Is_Thrown_When_A_User_That_Does_Not_Exist_Is_Searched(){
        RuntimeException exception = assertThrows (RuntimeException.class, () ->{
            diariesService.findByUserName("Ronaldo");
        });
        assertEquals("UserName does not exist", exception.getMessage());
    }

    @Test
    public void test_To_Delete_A_RegisteredUser_In_The_DiariesRepository(){
        diariesService.register("MorningStar", "ivar1234");
        assertEquals(1, diariesService.count());
        diariesService.register("Messi", "ivar1234");
        assertEquals(2, diariesService.count());
        diariesService.register("GodOfWar", "spartan12345");
        assertEquals(3, diariesService.count());
        diariesService.deleteByUserName("Messi");
        assertEquals(2, diariesService.count());
    }

    @Test
    public void test_To_Delete_A_User_That_Does_Not_Exist_In_The_DiariesRepository(){
        diariesService.register("MorningStar", "ivar1234");
        assertEquals(1, diariesService.count());
        diariesService.register("Messi", "ivar1234");
        assertEquals(2, diariesService.count());
        diariesService.register("GodOfWar", "spartan12345");
        assertEquals(3, diariesService.count());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            diariesService.deleteByUserName("GodHand");
        });
        assertEquals("UserName does not exist", exception.getMessage());
        assertEquals(3, diariesService.count());
    }
}
