package Loja_Virtual.Services;


import Loja_Virtual.Entities.Carrinho;
import Loja_Virtual.Entities.Produto;
import Loja_Virtual.Entities.Usuario;
import Loja_Virtual.Repository.CarrinhoRepository;
import Loja_Virtual.Repository.ProdutosRepository;
import Loja_Virtual.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarrinhoService {
    private final ProdutosRepository produtosRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;

    public Carrinho adicionarProduto(Long usuarioID, Long produtoId){
        Usuario usuario = usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.getCarrinho().getProdutos().add(produtoId);

        Produto produto = produtosRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            carrinho.getProdutos().add(produto);
            return carrinhoRepository.save(carrinho);

    }
}
