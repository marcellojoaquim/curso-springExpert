package vendasApi.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name")
    @NotEmpty(message = "{campo.produto-nome.obrigatorio}")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @Column(name = "price", precision = 20, scale = 2)
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal preco;

}
