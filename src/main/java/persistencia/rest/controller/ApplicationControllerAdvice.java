package persistencia.rest.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import persistencia.excepition.PedidoNaoEncontradoException;
import persistencia.excepition.RegraNegocioException;
import persistencia.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException( PedidoNaoEncontradoException ex){
        String msgError = ex.getMessage();
        return new ApiErrors(msgError);
    }
}
