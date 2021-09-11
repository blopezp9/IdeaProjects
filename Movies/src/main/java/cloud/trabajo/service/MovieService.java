package cloud.trabajo.service;

import cloud.trabajo.entity.Movies;

import java.util.List;

public interface MovieService {

    public List<Movies> listAllMovies();
    public Movies getMovie(Long id);
    public Movies createMovie(Movies movies);
    public Movies deleteMovie(Long id);
}
