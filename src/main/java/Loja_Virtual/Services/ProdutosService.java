package Loja_Virtual.Services;

import Loja_Virtual.DTOS.DescontoRequestDTO;
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

        novoProduto.setNome(dto.nome());
        novoProduto.setDescricao(dto.descricao());
        novoProduto.setPreco(dto.preco());
        novoProduto.setEstoque(dto.estoque());

        return produtosRepository.save(novoProduto);
    }

    public Produto atualizar(Long id, ProdutoRequestDTO dto){
        Produto atualizado = produtosRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        if (dto.nome() != null) {
            atualizado.setNome(dto.nome());
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

    public Produto aplicarDesconto(Long id, DescontoRequestDTO dto){
        Produto produto = produtosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        if(dto.percentual() < 0 || dto.percentual() >= 99){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Desconto inválido");
        }

        if(dto.fim().isBefore(dto.inicio())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data final inválida");
        }

        produto.setPercentualDesconto(dto.percentual());
        produto.setDescontoInicio(dto.inicio());
        produto.setDescontoFim(dto.fim());

        return produtosRepository.save(produto);
    }
}
