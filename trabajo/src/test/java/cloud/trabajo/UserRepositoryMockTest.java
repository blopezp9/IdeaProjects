package cloud.trabajo;

import cloud.trabajo.entity.Users;
import cloud.trabajo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest

public class UserRepositoryMockTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUsers_ThenResultListUsers(){
        Users.UsersBuilder user1 = Users.builder()
                .id(3L)
                .name("Bryan")
                .lastname("Lopez Parra");
        userRepository.save(user1.build());

        System.out.println(userRepository.findAll());
        List<Users> founds=userRepository.findByName(user1.build().getName());
        Assertions.assertThat(userRepository.findAll().size()).isEqualTo(3);
    }


}
