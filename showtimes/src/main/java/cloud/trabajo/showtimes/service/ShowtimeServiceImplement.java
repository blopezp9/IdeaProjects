package cloud.trabajo.showtimes.service;

import cloud.trabajo.showtimes.Repository.ShowtimeRepository;
import cloud.trabajo.showtimes.entity.Showtime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeServiceImplement implements ShowtimeService{

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeServiceImplement(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @Override
    public List<Showtime> listAllShowtime() {
        return showtimeRepository.findAll();
    }

    @Override
    public Showtime getShowtime(Long id) {
        return showtimeRepository.findById(id).orElse(null);
    }

    @Override
    public Showtime createShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public Showtime deleteShowtime(Long id) {

        Showtime showtime = getShowtime(id);
        if (null == showtime){
            return null;
        }
        showtimeRepository.delete(showtime);
        return showtime;
    }

    @Override
    public Showtime updateShowtime(Showtime showtime) {
        Showtime showtime1 = getShowtime(showtime.getId());
        if (null == showtime1){
            return null;
        }
        showtime1.setId(showtime.getId());
        showtime1.setDate(showtime.getDate());
        showtime1.setMovies(showtime.getMovies());
        return showtimeRepository.save(showtime1);

    }
}
