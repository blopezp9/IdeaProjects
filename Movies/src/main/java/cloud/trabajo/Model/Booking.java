package cloud.trabajo.Model;

import lombok.Data;

@Data
public class Booking {

    private Long id;
    private Long userid;
    private Long showtimeid;
    private String movies;
}
