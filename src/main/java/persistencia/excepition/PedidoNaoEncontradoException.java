package persistencia.excepition;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException() {
        super("Pedido nao encontrado");
    }

}
