package cloud.trabajo.client;

import cloud.trabajo.model.Booking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "booking-service")
@RequestMapping(value = "/bookings")
public interface BookingClient {

    @GetMapping
    public ResponseEntity<List<Booking>> listBooking();

}
