package cloud.trabajo.service;

import cloud.trabajo.client.BookingClient;
import cloud.trabajo.entity.Users;
import cloud.trabajo.model.Booking;
import cloud.trabajo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{


    private final UserRepository userRepository;

    @Autowired
    private BookingClient bookingClient;

    @Override
    public List<Users> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Users createUser(Users users) {

            return userRepository.save(users);
    }

    @Override
    public Users deleteUser(Long id) {
        Boolean b = false;
        Users usersDB = getUser(id);
        if (null == usersDB){
            return null;
        }

        List<Booking> bookings = bookingClient.listBooking().getBody();
        for (int i = 0; i<bookings.size(); i++) {

            if(bookings.get(i).getUserid()==usersDB.getId()){
                System.out.println("El usuario no puede ser eliminado porque tiene pendiente una reserva");
                b=true;
            }

        }

        if(b==true){
            return null;
        }

        userRepository.deleteById(id);
        return usersDB;
    }
}
