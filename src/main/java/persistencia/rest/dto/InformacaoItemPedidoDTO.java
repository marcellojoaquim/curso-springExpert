package persistencia.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder // a partir das proriedades gera uma classe builder e disponibiliza
public class InformacaoItemPedidoDTO {
    private String descrcaoProdito;
    private BigDecimal precoUnitario;
    private Integer quantidade;


}
