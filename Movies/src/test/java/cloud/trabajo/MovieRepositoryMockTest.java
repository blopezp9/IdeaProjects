package cloud.trabajo;

import cloud.trabajo.Repository.MovieRepository;
import cloud.trabajo.entity.Movies;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class MovieRepositoryMockTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void whenFindByMovies_ThenResultListMovies(){
        Movies.MoviesBuilder movies = Movies.builder()
                .id(2L)
                .title("Jhon Wick")
                .director("Bryan")
                .rating(3000);
        movieRepository.save(movies.build());
        List<Movies> founds=movieRepository.findByTitle(movies.build().getTitle());
        Assertions.assertThat(founds.size()).isEqualTo(1);
        System.out.println(movieRepository.findAll());
    }
}
