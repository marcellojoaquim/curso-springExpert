package persistencia.rest.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import persistencia.domain.entity.Client;
import persistencia.domain.repository.ClienteRepository;

import java.util.List;

// @Controller esta anotacao precisa que os métodos sejam anotados com @ResponseBody
@RestController  //Elimina a necessidade da anotação @ResponseBody de forma explícita
@RequestMapping("/api/clientes")
public class ClientController {

    private ClienteRepository clientes;

    public ClientController(ClienteRepository clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    public Client getClientById( @PathVariable Integer id){
        return clientes.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));
    }

    @GetMapping
    public List<Client> find(Client filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client){
        return clientes.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clientes.findById(id)
                .map(client -> { clientes.delete(client);
                return client;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id ,@RequestBody Client cliente){
         clientes.findById(id)
                .map(clienteExist -> {
                    cliente.setId(clienteExist.getId());
                    clientes.save(cliente);
                    return clienteExist;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

    }

}
