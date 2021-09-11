package cloud.trabajo.service;

import cloud.trabajo.entity.Users;

import java.util.List;

public interface UserService {

    public List<Users> listAllUsers();
    public Users getUser(Long id);
    public Users createUser(Users users);
    public Users deleteUser(Long id);
}
