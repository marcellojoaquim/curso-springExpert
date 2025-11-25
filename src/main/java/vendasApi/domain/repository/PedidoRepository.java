package vendasApi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vendasApi.domain.entity.Client;
import vendasApi.domain.entity.Pedido;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findByClient(Client client);

    @Query("select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
