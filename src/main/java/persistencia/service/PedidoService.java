package persistencia.service;

import persistencia.domain.entity.Pedido;
import persistencia.domain.enums.StatusPedido;
import persistencia.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoComplento(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
