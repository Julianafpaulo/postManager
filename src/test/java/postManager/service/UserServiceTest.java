package postManager.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import postManager.domain.User;
import postManager.domain.exception.UserNotFoundException;
import postManager.repository.UserRepository;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() {
        User user = new User(11L, "Produto", 8);

        when(repository.findById(user.getId()))
                .thenReturn(java.util.Optional.ofNullable(user));
        when(repository.save(any())).thenReturn(user);
    }

    @Test
    public void whenValidId_thenUserShouldBeFoundSucess() {
        Long id = (long) 11;

        User found = userService.getUser(id);

        verify(repository).findById(eq(11L));
        Assertions.assertThat(found)
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test(expected = UserNotFoundException.class)
    public void whenValidId_thenUserShouldBeFoundFail() {
        Long id = (long) 1;
        User found = userService.getUser(id);

        verify(repository).findById(eq(11L));
        Assertions.assertThat(found)
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isNotNull();
        Assertions.assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test
    public void whenValidId_thenUserShouldDeleteSucess() {
        Long id = (long) 11;
        HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
        ResponseEntity a = userService.deleteUser(id);

        Assertions.assertThat(a.getStatusCode())
                .isEqualTo(a2);
    }

    @Test(expected = UserNotFoundException.class)
    public void whenValidId_thenUserShouldDeleteFail() {
        Long id = (long) 1;
        HttpStatus a2 = ResponseEntity.ok().build().getStatusCode();
        ResponseEntity a = userService.deleteUser(id);

        Assertions.assertThat(a.getStatusCode())
                .isEqualTo(a2);
    }

    @Test
    public void whenValidId_thenUserShouldUpdateSucess() {
        Long id = 11L;
        User found = new User(11L, "Produto atualizado", 8);

        User user = userService.updateUser(id, found);

        Assertions.assertThat(user.getName()).isEqualTo("Produto atualizado");
    }

    @Test(expected = UserNotFoundException.class)
    public void whenValidId_thenUserShouldUpdateFail() {
        Long id = 1L;
        User found = new User(1L, "Produto atualizado", 8);

        User user = userService.updateUser(id, found);

        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getName()).isEqualTo("Produto atualizado");
    }

    @Test
    public void whenValidUser_thenUserShouldAddSucess(){
        User user = new User(1L, "Produto", 8);

        user = userService.addUser(user);

        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getName()).isEqualTo("Produto");
    }
}