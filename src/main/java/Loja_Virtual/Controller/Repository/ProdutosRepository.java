package Loja_Virtual.Controller.Repository;

import Loja_Virtual.Entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Long> {

    List<Produto> id(Long id);
}
