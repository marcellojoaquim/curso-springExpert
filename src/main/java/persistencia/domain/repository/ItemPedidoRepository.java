package persistencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistencia.domain.entity.ItemPedido;


public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
