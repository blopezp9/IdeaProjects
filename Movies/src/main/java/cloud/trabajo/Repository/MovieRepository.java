package cloud.trabajo.Repository;

import cloud.trabajo.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movies, Long> {

    public List<Movies> findByTitle(String title);
}
