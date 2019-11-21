package postManager_integrationTests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import postManager.Main;
import postManager.domain.User;
import postManager.repository.UserRepository;

import java.util.List;

import static java.util.Arrays.asList;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Main.class)
@AutoConfigureMockMvc
public class

UserEndpointTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private UserRepository repository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void listUsersShouldReturnStatusCode200(){
        List<User> users = asList(new User(1L,"Usuario",10));
        BDDMockito.when(repository.findAll()).thenReturn(users);
        ResponseEntity<String> response = restTemplate.getForEntity("/users/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getUserByIdShouldReturnStatusCode200(){
        User user = new User(1L,"Usuario",10);
        BDDMockito.when(repository.findById(user.getId())).thenReturn(java.util.Optional.ofNullable(user));
        ResponseEntity<String> response = restTemplate.getForEntity("/users/{id}", String.class, user.getId());
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void getUserByIdAndUserDoesntExistShouldReturnStatusCode404(){
        ResponseEntity<String> response = restTemplate.getForEntity("/users/{id}", String.class, -1);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}

