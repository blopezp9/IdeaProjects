package cloud.trabajo.client;

import cloud.trabajo.model.Booking;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BookingHystrixFallbackFactory implements BookingClient{
    @Override
    public ResponseEntity<List<Booking>> listBooking() {
        
        
       List<Booking> bookingBuilders = Collections.singletonList(Booking.builder()
               .id(0L)
               .showtimeid(0L)
               .userid(0L)
               .movies("None").build());


        return ResponseEntity.ok(bookingBuilders);
    }
}
