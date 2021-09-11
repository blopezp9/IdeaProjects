package cloud.trabajo.repository;

import cloud.trabajo.entity.Showtimes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepository  extends JpaRepository<Showtimes, Long> {


}
