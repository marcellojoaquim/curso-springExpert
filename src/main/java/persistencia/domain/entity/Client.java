package persistencia.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Client(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Client(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}
