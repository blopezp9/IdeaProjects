package cloud.trabajo.showtimes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShowtimesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowtimesApplication.class, args);
    }

}
