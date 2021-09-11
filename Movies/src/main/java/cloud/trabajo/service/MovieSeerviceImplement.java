package cloud.trabajo.service;

import cloud.trabajo.Repository.MovieRepository;
import cloud.trabajo.entity.Movies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieSeerviceImplement implements MovieService {

    private final MovieRepository movieRepository;

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

        Movies moviesDB = getMovie(id);

        if (null == moviesDB){
            return null;
        }
        movieRepository.deleteById(id);
        return moviesDB;
    }
}
