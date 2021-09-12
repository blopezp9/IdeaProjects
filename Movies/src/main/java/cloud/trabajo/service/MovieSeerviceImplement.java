package cloud.trabajo.service;

import cloud.trabajo.Model.Booking;
import cloud.trabajo.Model.Showtime;
import cloud.trabajo.Repository.MovieRepository;
import cloud.trabajo.client.BookingClient;
import cloud.trabajo.client.ShowtimeClient;
import cloud.trabajo.entity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieSeerviceImplement implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    private ShowtimeClient showtimeClient;

    @Autowired
    private BookingClient bookingClient;

    @Override
    public List<Movies> listAllMovies() {
        System.out.println(movieRepository.findAll());

        return movieRepository.findAll();
    }

    public MovieSeerviceImplement(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movies getMovie(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movies createMovie(Movies movies) {
        return movieRepository.save(movies);
    }

    @Override
    public Movies deleteMovie(Long id) {
        String cadenaDondeBuscar = "";
        String loQueQuieroBuscar = "";
        Boolean aBoolean=false;
        Movies moviesDB = getMovie(id);

        if (null == moviesDB){
            return null;
        }
        //Busca si hay movies asociadas a showtimes
        List<Showtime> showtime = showtimeClient.listshowtime().getBody();
        for (int i = 0; i<showtime.size(); i++) {
            cadenaDondeBuscar=showtime.get(i).getMovies();
            loQueQuieroBuscar=getMovie(id).getTitle();
            String[] palabras = loQueQuieroBuscar.split("\\W+");
            for (String palabra : palabras) {
                if (cadenaDondeBuscar.contains(palabra)) {
                    System.out.println("Movies encontradas, no es posible eliminarse ya que está asociada a una programación");
                    aBoolean=true;
                    Showtime showtime1 = showtimeClient.listshowtimeid(showtime.get(i).getId()).getBody();
                    moviesDB.setShowtime(showtime1);
                }
            }

        }

        List<Booking> bookings = bookingClient.listBooking().getBody();
        for (int i = 0; i<bookings.size(); i++) {
            cadenaDondeBuscar=bookings.get(i).getMovies();
            loQueQuieroBuscar=getMovie(id).getTitle();
            String[] palabras = loQueQuieroBuscar.split("\\W+");
            for (String palabra : palabras) {
                if (cadenaDondeBuscar.contains(palabra)) {
                    System.out.println("Movies encontradas, no es posible eliminarse ya que está asociada a una reserva");
                    aBoolean=true;
                    ResponseEntity<Booking> bk = bookingClient.listbookingID(bookings.get(i).getId());
                    moviesDB.setBooking(bk.getBody());
                }
            }

        }



        if(aBoolean==true){
            return null;
        }

        movieRepository.deleteById(id);
        return moviesDB;
    }
}
