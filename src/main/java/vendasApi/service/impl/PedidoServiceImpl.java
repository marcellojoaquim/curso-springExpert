package vendasApi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vendasApi.domain.entity.Client;
import vendasApi.domain.entity.ItemPedido;
import vendasApi.domain.entity.Pedido;
import vendasApi.domain.entity.Product;
import vendasApi.domain.enums.StatusPedido;
import vendasApi.domain.repository.ClienteRepository;
import vendasApi.domain.repository.ItemPedidoRepository;
import vendasApi.domain.repository.PedidoRepository;
import vendasApi.domain.repository.ProductRepository;
import vendasApi.excepition.PedidoNaoEncontradoException;
import vendasApi.excepition.RegraNegocioException;
import vendasApi.rest.dto.ItemPedidoDTO;
import vendasApi.rest.dto.PedidoDTO;
import vendasApi.service.PedidoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductRepository productRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    @Override
    @Transactional // se ocorrer erro é dado rollback, garante integridade
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Client cli = clienteRepository
                .findById(idCliente)
                .orElseThrow(()-> new RegraNegocioException("Codigo cliente invalido"));
        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setClient(cli);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoComplento(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepository.findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar pedido sem itens.");
        }
        return itens
                .stream()
                .map( dto ->{
                    Integer idProduto = dto.getProduto();
                    Product produto = productRepository
                            .findById(idProduto)
                            .orElseThrow(()->new RegraNegocioException("Codigo de produto inválido: "+idProduto
                            ));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuatidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduct(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
