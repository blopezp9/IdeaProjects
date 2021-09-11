package cloud.trabajo;

import cloud.trabajo.entity.Users;
import cloud.trabajo.repository.UserRepository;
import cloud.trabajo.service.UserService;
import cloud.trabajo.service.UserServiceImplement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

//Realizará una busqueda del usuario Mockeado y cuando se utilice el metodo para obtener al usuario, debe devolver el usuario mockeado
@SpringBootTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImplement(userRepository);
        Users user01 = Users.builder()
                .id(2L)
                .name("Bryan")
                .lastname("Lopez Parra")
                .build();

        Mockito.when(userRepository.findById(2L))
                .thenReturn(Optional.of(user01));
    }

    @Test
    public void whenValidGetID_TheReturnUser(){
        Users found = userService.getUser(2L);
        Assertions.assertThat(found.getName()).isEqualTo("Bryan");


    }

}
