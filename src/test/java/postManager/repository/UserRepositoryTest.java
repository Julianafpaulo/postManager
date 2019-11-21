package postManager.repository;

import org.assertj.core.api.Assertions;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import postManager.domain.User;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void createShouldPersistDataSucess(){

        User user = new User("Usuario",8);
        this.repository.save(user);
        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getName()).isEqualTo("Usuario");
        Assertions.assertThat(user.getEmail()).isEqualTo(8);

    }

    @Test(expected = ComparisonFailure.class)
    public void createShouldPersistDataFail(){

        User user = new User("Usuario1",8);
        this.repository.save(user);
        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getName()).isEqualTo("Usuario");
        Assertions.assertThat(user.getEmail()).isEqualTo(8);
    }

    @Test
    public void findUserAfterSave() {

        User user = new User("Usuario", 10);
        repository.save(user);
        List<User> users = repository.findAll();
        assertEquals(4, users.size());
        int size = users.size() - 1 ;
        Assertions.assertThat(users.get(size).getId()).isNotNull();
        Assertions.assertThat(users.get(size).getEmail()).isEqualTo(10);
        Assertions.assertThat(users.get(size).getName()).isEqualTo("Usuario");

    }

    @Test
    public void deleteUserAfterSave() {

        User user = new User("Usuario", 10);
        repository.save(user);
        List <User> foundUsers = repository.findAll();
        repository.delete(foundUsers.get(0));
        List<User> users = repository.findAll();
        assertEquals(3, users.size());

    }

    @Test
    public void updateUserAfterSave() {

        User user = new User("Usuario", 10);
        repository.save(user);
        user.setName("Usuario Atualizado");
        repository.save(user);
        List <User> users = repository.findAll();
        int size = users.size() - 1;
        assertEquals(4, users.size());
        assertEquals("Usuario Atualizado", users.get(size).getName());

    }

    @Test
    public void returnEmptyWheNotFound(){
        Optional<User> found;
        found = repository.findById(111L);
        Assertions.assertThat(!found.isPresent());
    }

}
