package postManager.controller;

import net.minidev.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import postManager.domain.User;
import postManager.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureDataJpa
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {

        User user = new User(11L,"Produto",8);

        List<User> allUsers = Arrays.asList(user);

        given(service.getAllUsers()).willReturn(allUsers);

        mvc.perform(get("/users/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Produto"));
    }

    @Test
    public void whenpostUser_thenReturnCreated() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Produto");
        jsonObject.put("price", 10.0);
        mvc.perform(post("/users/")
                .contentType("application/json;charset=UTF-8")
                .content(String.valueOf(jsonObject)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void whenputUser_thenReturnOk() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Produto");
        jsonObject.put("price", 10.0);
        mvc.perform(put("/users/{id}", 1L)
                .contentType("application/json;charset=UTF-8")
                .content(String.valueOf(jsonObject)))
                .andExpect(status().isOk());
    }

    @Test
    public void whendeleteUser_thenReturnOk() throws Exception {

                mvc.perform(delete("/users/{id}", 1l))
                .andExpect(status().isOk());
    }

}
