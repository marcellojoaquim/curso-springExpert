package vendasApi.rest.controller;

import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vendasApi.domain.entity.Client;
import vendasApi.domain.repository.ClienteRepository;

import javax.validation.Valid;
import java.util.List;

// @Controller esta anotacao precisa que os métodos sejam anotados com @ResponseBody
@RestController  //Elimina a necessidade da anotação @ResponseBody de forma explícita
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClientController {

    private ClienteRepository repository;

    public ClientController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca cliente por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    public Client getClientById( @PathVariable @ApiParam("ID do cliente") Integer id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de clientes")
    @ApiResponse(code=200, message = "Clientes encontrados")
    public List<Client> find(Client filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING);
        Example<Client> example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um cliente")
    @ApiResponses({
            @ApiResponse(code=200,message ="Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public Client save(@RequestBody @Valid Client client){
        return repository.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deleta um cliente")
    @ApiResponses({
            @ApiResponse(code=204,message ="No content"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    public void delete(@PathVariable Integer id){
        repository.findById(id)
                .map(client -> { repository.delete(client);
                return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Atualiza um cliente")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    public void update( @PathVariable Integer id ,@RequestBody Client cliente){
         repository.findById(id)
                .map(clienteExist -> {
                    cliente.setId(clienteExist.getId());
                    repository.save(cliente);
                    return clienteExist;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

    }

}
