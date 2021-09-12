package cloud.trabajo.client;

import cloud.trabajo.Model.Showtime;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "showtime-service")
@RequestMapping(value = "/showtimes")
public interface ShowtimeClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Showtime> listshowtimeid(@PathVariable("id") Long id);

    @GetMapping
    public ResponseEntity<List<Showtime>> listshowtime();
}
