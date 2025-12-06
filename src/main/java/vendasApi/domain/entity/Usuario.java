package vendasApi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotBlank(message = "{campo.login.obrigatorio}")
    private String login;

    @Column
    @NotBlank(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column
    private boolean admin;
}
