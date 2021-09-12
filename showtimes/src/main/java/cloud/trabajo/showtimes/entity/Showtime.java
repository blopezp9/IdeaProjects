package cloud.trabajo.showtimes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbl_showtimes")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "El ID es obligatorio")
        private Long id;
        @Temporal(TemporalType.DATE)
        @NotNull(message = "La fecha es obligatoria")
        private Date date;
        private String movies;

}