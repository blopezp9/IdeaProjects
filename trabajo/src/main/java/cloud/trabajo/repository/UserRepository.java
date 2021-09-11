package cloud.trabajo.repository;

import cloud.trabajo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {

    //public List<Users> findByName(Users name);

    public List<Users> findByName(String name);
}
