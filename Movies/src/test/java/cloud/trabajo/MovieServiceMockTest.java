package cloud.trabajo;

import cloud.trabajo.Repository.MovieRepository;
import cloud.trabajo.entity.Movies;
import cloud.trabajo.service.MovieSeerviceImplement;
import cloud.trabajo.service.MovieService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MovieServiceMockTest {

    @Mock
    private MovieRepository movieRepository;

    private MovieService movieService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        movieService = new MovieSeerviceImplement(movieRepository);
        Movies user01 =Movies.builder()
                .id(2L)
                .title("Bryan")
                .director("Lopez Parra")
                .rating(5000)
                .build();

        Mockito.when(movieRepository.findById(2L))
                .thenReturn(Optional.of(user01));
    }

    @Test
    public void whenValidGetID_TheReturnUser(){
        Movies found = movieService.getMovie(2L);
        Assertions.assertThat(found.getTitle()).isEqualTo("Bryan");


    }
}
