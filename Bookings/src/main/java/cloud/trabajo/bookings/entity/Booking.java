package cloud.trabajo.bookings.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_bookings")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "El ID es obligatorio")
    private Long id;
    @NotNull(message = "El userID es obligatorio")
    private Long userid;
    @NotNull(message = "El showtimeID es obligatorio")
    private Long showtimeid;
    private String movies;
}
