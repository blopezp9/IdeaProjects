package cloud.trabajo.Model;

import lombok.Data;
;
import java.util.Date;

@Data
public class Showtime {

    private Long id;
    private Date date;
    private String movies;
}
