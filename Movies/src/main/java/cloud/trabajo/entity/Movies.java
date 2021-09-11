package cloud.trabajo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_movies")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "El campo debe ser obligatorio")
    @Column(name = "id", unique = true)
    private Long id;

    @NotEmpty(message = "El campo no debe estar vacío")
    private String title;
    @NotEmpty(message = "El campo no debe estar vacío")
    private String director;

    @Range(min = 1, max = 5, message = "El intervalo del rating es de 1 a 5")
    private int rating;
}
