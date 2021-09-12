package cloud.trabajo.bookings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingsApplication.class, args);
    }

}
