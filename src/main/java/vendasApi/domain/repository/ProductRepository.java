package vendasApi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vendasApi.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer > {

}
