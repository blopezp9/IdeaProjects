package cloud.trabajo.Controller;

import cloud.trabajo.client.BookingClient;
import cloud.trabajo.client.ShowtimeClient;
import cloud.trabajo.entity.Movies;
import cloud.trabajo.service.MovieService;
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
@RequestMapping (value = "/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movies>> listmovie(){

        List<Movies> movies = movieService.listAllMovies();
        if (movies.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movies> listmovieId(@PathVariable("id") Long id){
        Movies movies = movieService.getMovie(id);
        if (movies.getId()==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<Movies> CreateMovie(@Valid @RequestBody Movies movies, BindingResult result){

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Movies  moviecreate = movieService.createMovie(movies);
        return ResponseEntity.status(HttpStatus.CREATED).body(moviecreate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movies> delemovies(@PathVariable ("id") Long id){

        Movies moviesdelete = movieService.deleteMovie(id);
        if (moviesdelete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moviesdelete);

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
