package cloud.trabajo.bookings.Repository;

import cloud.trabajo.bookings.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    public List<Booking> findByUserid(Long id);
    public List<Booking> findByShowtimeid(Long id);
    public List<Booking> findByMovies(Long id);
}
