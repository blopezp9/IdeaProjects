package cloud.trabajo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking {

    private Long id;
    private Long userid;
    private Long showtimeid;
    private String movies;
}
