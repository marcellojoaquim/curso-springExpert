package persistencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistencia.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer > {

}
