package cloud.trabajo.entity;
import lombok.*;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.config.RepositoryBeanDefinitionParser;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_users")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "El campo debe ser obligatio")
    private Long id;
    @NotEmpty(message = "El nombre no debe estar vacío")
    private String name;
    @NotEmpty(message = "Los apellidos no deben estar vacíos")
    private String lastname;

}
