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

    private ClienteRepository repository;

    public ClientController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Client getClientById( @PathVariable Integer id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));
    }

    @GetMapping
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
    public Client save(@RequestBody Client client){
        return repository.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.findById(id)
                .map(client -> { repository.delete(client);
                return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id ,@RequestBody Client cliente){
         repository.findById(id)
                .map(clienteExist -> {
                    cliente.setId(clienteExist.getId());
                    repository.save(cliente);
                    return clienteExist;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

    }

}
