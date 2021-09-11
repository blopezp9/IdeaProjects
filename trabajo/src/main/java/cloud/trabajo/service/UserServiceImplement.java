package cloud.trabajo.service;

import cloud.trabajo.entity.Users;
import cloud.trabajo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{


    private final UserRepository userRepository;

    @Override
    public List<Users> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Users createUser(Users users) {

            return userRepository.save(users);
    }

    @Override
    public Users deleteUser(Long id) {

        Users usersDB = getUser(id);
        if (null == usersDB){
            return null;
        }
        userRepository.deleteById(id);
        return usersDB;
    }
}
