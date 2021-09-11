package cloud.trabajo.bookings.Service;

import cloud.trabajo.bookings.Repository.BookingRepository;
import cloud.trabajo.bookings.entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImplement implements BookingService{

    private final BookingRepository bookingRepository;

    public BookingServiceImplement(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> listAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking deleteBooking(Long id) {
        Booking booking = getBooking(id);
        if (null == booking){
            return null;
        }
        bookingRepository.delete(booking);
        return booking;
    }

    @Override
    public Booking getBookinguserID(Long id) {
        Long n=0L;
        List<Booking> bd = bookingRepository.findByUserid(id);

        for (int i = 0; i<bd.size(); i++) {
            n=bd.get(i).getId();
        }
        return bookingRepository.findById(n).orElse(null);
    }


}
