package cloud.trabajo.bookings.Service;

import cloud.trabajo.bookings.entity.Booking;

import java.util.List;

public interface BookingService {

    public List<Booking> listAllBooking();
    public Booking getBooking(Long id);
    public Booking createBooking(Booking booking);
    public Booking deleteBooking(Long id);
    public Booking getBookinguserID(Long id);
}
