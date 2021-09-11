package cloud.trabajo.bookings.Controller;

import cloud.trabajo.bookings.Service.BookingService;
import cloud.trabajo.bookings.entity.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> listBooking(){

        List<Booking> booking =bookingService.listAllBooking();
        if (booking.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(booking);

    }

    @PostMapping
    public ResponseEntity<Booking> Createbooking(@Valid @RequestBody Booking booking, BindingResult result){

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Booking  booking1 = bookingService.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking1);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Booking> listbookingID(@PathVariable("id") Long id){
        Booking booking = bookingService.getBooking(id);
        if (booking.getId()==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> deletebooking(@PathVariable ("id") Long id){

        Booking bookingdelete = bookingService.deleteBooking(id);
        if (bookingdelete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookingdelete);

    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Booking> listbookinUsergID(@PathVariable("id") Long id){
        Booking booking = bookingService.getBookinguserID(id);
        if (booking.getId()==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(booking);
    }

    private String formatMessage(BindingResult result){

        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .message(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
