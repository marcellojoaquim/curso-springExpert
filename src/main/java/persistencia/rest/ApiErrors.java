package persistencia.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApiErrors {

    @Getter
    private List<String> erros;

    public ApiErrors(String mensagemErro){
        this.erros = Arrays.asList(mensagemErro);
    }


}
