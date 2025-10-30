package persistencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import persistencia.domain.entity.Client;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Integer > {

    @Query(value = "select c from Client c where c.nome like :nome")
    List<Client> encontrarPorNome(@Param("nome") String nome);
    List<Client> findByNomeLike(String nome);
    List<Client> findByNomeStartingWith(String nome);
    List<Client> findByNomeEndingWith(String nome);
    List<Client> findByNomeLikeOrId(String nome, Integer id);
    boolean existsByNomeLike(String nome);

    @Query("select c from Client c left join fetch c.pedidos p where c.id = :id ")
    Client findClientFetchPedido( @Param("id") Integer id);


}
