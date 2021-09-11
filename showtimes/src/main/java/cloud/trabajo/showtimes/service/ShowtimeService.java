package cloud.trabajo.showtimes.service;

import cloud.trabajo.showtimes.entity.Showtime;

import java.util.List;

public interface ShowtimeService {

    public List<Showtime> listAllShowtime();
    public Showtime getShowtime(Long id);
    public Showtime createShowtime(Showtime showtime);
    public Showtime deleteShowtime(Long id);
    public Showtime updateShowtime(Showtime showtime);
}
