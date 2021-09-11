package cloud.trabajo.repository;

import cloud.trabajo.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Bookings, Long> {

}
