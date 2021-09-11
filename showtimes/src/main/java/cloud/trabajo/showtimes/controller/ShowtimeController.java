package cloud.trabajo.showtimes.controller;

import cloud.trabajo.showtimes.entity.Showtime;
import cloud.trabajo.showtimes.service.ShowtimeService;
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
@RequestMapping(value = "/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping
    public ResponseEntity<List<Showtime>> listshowtime(){

        List<Showtime> showtime =showtimeService.listAllShowtime();
        if (showtime.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showtime);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Showtime> listshowtimeid(@PathVariable("id") Long id){
        Showtime showtime = showtimeService.getShowtime(id);
        if (showtime.getId()==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showtime);
    }

    @PostMapping
    public ResponseEntity<Showtime> Createshowtime(@Valid @RequestBody Showtime showtime, BindingResult result){

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Showtime  showtime1 = showtimeService.createShowtime(showtime);
        return ResponseEntity.status(HttpStatus.CREATED).body(showtime1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Showtime> deleshowtimes(@PathVariable ("id") Long id){

        Showtime showtimedelete = showtimeService.deleteShowtime(id);
        if (showtimedelete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(showtimedelete);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Showtime> updateshowtime(@PathVariable("id") Long id, @RequestBody Showtime showtime){
        showtime.setId(id);
        Showtime showtimebd = showtimeService.updateShowtime(showtime);
        if(showtimebd == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(showtimebd);

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
