package persistencia.excepition;

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String message) {
        super(message);
    }
}
