package Loja_Virtual.Services;

import Loja_Virtual.Repository.ProdutosRepository;
import Loja_Virtual.DTOS.ProdutoRequestDTO;
import Loja_Virtual.Entities.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ProdutosService {

    private final ProdutosRepository produtosRepository;


    public List<Produto> listarProdutos(){return produtosRepository.findAll();}

    public Produto criar(ProdutoRequestDTO dto){
        Produto novoProduto = new Produto();

        novoProduto.setNome_produto(dto.nome());
        novoProduto.setDescricao(dto.descricao());
        novoProduto.setPreco(dto.preco());
        novoProduto.setEstoque(dto.estoque());

        return produtosRepository.save(novoProduto);
    }

    public Produto atualizar(Long id, ProdutoRequestDTO dto){
        Produto atualizado = produtosRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        if (dto.nome() != null) {
            atualizado.setNome_produto(dto.nome());
        }
        if (dto.preco() != null) {
            atualizado.setPreco(dto.preco());
        }
        if (dto.descricao() != null) {
            atualizado.setDescricao(dto.descricao());
        }
        if (dto.estoque() != null) {
            atualizado.setEstoque(dto.estoque());
        }

        return produtosRepository.save(atualizado);
    }

    public Produto deletar(Long id){
        Produto deletado = produtosRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        produtosRepository.delete(deletado);
        return deletado;
    }
}
