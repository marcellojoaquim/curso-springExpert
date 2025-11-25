package vendasApi.rest.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vendasApi.domain.entity.ItemPedido;
import vendasApi.domain.entity.Pedido;
import vendasApi.domain.enums.StatusPedido;
import vendasApi.rest.dto.AtualizacaoStatusPedidoDTO;
import vendasApi.rest.dto.InformacaoItemPedidoDTO;
import vendasApi.rest.dto.InformacoesPedidoDTO;
import vendasApi.rest.dto.PedidoDTO;
import vendasApi.service.PedidoService;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO pedidoDTO){
        Pedido pedido = service.salvar(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoComplento(id)
                .map( p -> converter(p))
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND, "Pedido nao encontrado"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyy")))
                .cpf(pedido.getClient().getCpf())
                .nomeCliente(pedido.getClient().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converter(pedido.getItens()))
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(
            @PathVariable Integer id,
            @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
            return itens
                    .stream()
                    .map(item -> InformacaoItemPedidoDTO
                            .builder()
                                    .descrcaoProdito(item.getProduct().getDescricao())
                                    .precoUnitario(item.getProduct().getPreco())
                                    .quantidade(item.getQuatidade())
                                    .build()
                    ).collect(Collectors.toList());
    }
}
