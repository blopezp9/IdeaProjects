package cloud.trabajo.showtimes.Repository;

import cloud.trabajo.showtimes.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    public List<Showtime> findByMovies(String name);
}
