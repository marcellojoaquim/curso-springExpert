package persistencia.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "description")
    private String descricao;

    @Column(name = "price", precision = 20, scale = 2)
    private BigDecimal preco;

}
