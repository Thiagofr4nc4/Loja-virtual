package Loja_Virtual.Repository;

import Loja_Virtual.Entities.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    List<Carrinho> id(Long id);

}
