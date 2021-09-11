package cloud.trabajo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_Bookings")
@Data
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userid;
    private Long showtimeid;
    private String movies;
}
