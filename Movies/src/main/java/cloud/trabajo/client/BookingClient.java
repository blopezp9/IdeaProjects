package cloud.trabajo.client;

import cloud.trabajo.Model.Booking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "booking-service")
@RequestMapping(value = "/bookings")
public interface BookingClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Booking> listbookingID(@PathVariable("id") Long id);

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Booking> listbookinUsergID(@PathVariable("id") Long id);

    @GetMapping
    public ResponseEntity<List<Booking>> listBooking();
}
