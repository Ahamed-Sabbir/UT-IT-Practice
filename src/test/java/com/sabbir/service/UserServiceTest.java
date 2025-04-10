package com.sabbir.service;

import com.sabbir.model.User;
import com.sabbir.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void testFindById_success(){
        //Given
        User user = new User(1L, "samia", "samia@gmail.com", 23);

        //When
        Mockito.when(userRepo.findUserById(1L)).thenReturn((user));

        //Assert
        User user1 = userService.findById(1L);
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(1L, user1.getId());
        Assertions.assertEquals("samia", user1.getName());
        Assertions.assertEquals("samia@gmail.com", user1.getEmail());
        Assertions.assertEquals(23, user1.getAge());

    }

    @Test
    void testFindById_failure(){
        //Given

        //When

        //Assert
        RuntimeException thrownException = Assertions.assertThrows(RuntimeException.class, () -> userService.findById(1L));
        Assertions.assertEquals("No user found with id: " + 1, thrownException.getMessage());
    }

    @Test
    void testFindByEmail_success(){
        //Given
        User user = new User(1L, "samia", "samia@gmail.com", 23);

        //When
        Mockito.when(userRepo.findByEmail("samia@gmail.com")).thenReturn((user));

        //Assert
        User user1 = userService.findByEmail("samia@gmail.com");
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(1L, user1.getId());
        Assertions.assertEquals("samia", user1.getName());
        Assertions.assertEquals("samia@gmail.com", user1.getEmail());
        Assertions.assertEquals(23, user1.getAge());

    }

    @Test
    void testFindByEmail_failure(){
        //Given

        //When

        //Assert
        RuntimeException thrownException = Assertions.assertThrows(RuntimeException.class, () -> userService.findByEmail("samia@gmail.com"));
        Assertions.assertEquals("No user found with email: samia@gmail.com", thrownException.getMessage());
    }

    @Test
    void testSave_success(){
        //Given
        User user = new User(1L, "samia", "samia@gmail.com", 23);

        //When
        Mockito.when(userRepo.save(user)).thenReturn((user));

        //Assert
        User user1 = userService.save(user);
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(1L, user1.getId());
        Assertions.assertEquals("samia", user1.getName());
        Assertions.assertEquals("samia@gmail.com", user1.getEmail());
        Assertions.assertEquals(23, user1.getAge());

    }

    @Test
    void testSave_failure(){
        //Given
        User user = new User(1L, "samia", "samia@gmail.com", 23);

        //When
        Mockito.when(userRepo.findByEmail("samia@gmail.com")).thenReturn(user);

        //Assert
        RuntimeException thrownException = Assertions.assertThrows(RuntimeException.class, () -> userService.save(user));
        Assertions.assertEquals("User already exists with email: samia@gmail.com", thrownException.getMessage());
    }

    @Test
    void testUpdate_success(){
        //Given
        User existingUser = new User(1L, "samia", "samia@gmail.com", 23);
        User updatedUser = new User(1L, "user 2", "user2@gmail.com", 23);

        //When
        Mockito.when(userRepo.findUserById(1L)).thenReturn(existingUser);
        Mockito.when(userRepo.save(Mockito.any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Assert
        User user1 = userService.update(updatedUser);
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(1L, user1.getId());
        Assertions.assertEquals("user 2", user1.getName());
        Assertions.assertEquals("user2@gmail.com", user1.getEmail());
        Assertions.assertEquals(23, user1.getAge());
    }

    @Test
    void testUpdate_failure(){
        //Given
        User existingUser = new User(1L, "samia", "samia@gmail.com", 23);

        //When
        Mockito.when(userRepo.findUserById(1L)).thenReturn(null);

        //Assert
        RuntimeException thrownException = Assertions.assertThrows(RuntimeException.class, () -> userService.update(existingUser));
        Assertions.assertEquals("User not exists with id: " + 1L, thrownException.getMessage());

    }
}
