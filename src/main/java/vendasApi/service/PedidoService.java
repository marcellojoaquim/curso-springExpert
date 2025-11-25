package vendasApi.service;

import vendasApi.domain.entity.Pedido;
import vendasApi.domain.enums.StatusPedido;
import vendasApi.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoComplento(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
