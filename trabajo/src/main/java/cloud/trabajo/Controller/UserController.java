package cloud.trabajo.Controller;

import cloud.trabajo.entity.Users;
import cloud.trabajo.service.UserService;
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

//video 8
@RestController
@RequestMapping (value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> listuser(){
        List<Users> users = userService.listAllUsers();
        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Users> CreateUser(@Valid @RequestBody Users users, BindingResult result){

        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Users usercreate = userService.createUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(usercreate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Users> deleusers(@PathVariable ("id") Long id){

        Users usersdelete = userService.deleteUser(id);
        if (usersdelete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usersdelete);

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
