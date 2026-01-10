package Loja_Virtual.Services;


import Loja_Virtual.DTOS.CarrinhoDTO;
import Loja_Virtual.DTOS.ItemCarrinhoDTO;
import Loja_Virtual.Entities.Carrinho;
import Loja_Virtual.Entities.ItemCarrinho;
import Loja_Virtual.Entities.Produto;
import Loja_Virtual.Entities.Usuario;
import Loja_Virtual.Repository.CarrinhoRepository;
import Loja_Virtual.Repository.ProdutosRepository;
import Loja_Virtual.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarrinhoService {
    private final ProdutosRepository produtosRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Carrinho adicionarProduto(Long usuarioID, Long produtoId, int quantidade){

        if(quantidade <= 0){
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        Usuario usuario = usuarioRepository.findById(usuarioID)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Carrinho carrinho = usuario.getCarrinho();

        Produto produto = produtosRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ItemCarrinho itemExistente = carrinho.getItens().stream()
                .filter(item -> item.getProduto().getId().equals(produtoId))
                .findFirst()
                .orElse(null);

        if (itemExistente != null) {
            itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
        } else {
            ItemCarrinho novoItem = new ItemCarrinho();
            novoItem.setCarrinho(carrinho);
            novoItem.setProduto(produto);
            novoItem.setQuantidade(quantidade);

            carrinho.getItens().add(novoItem);
        }

        return carrinhoRepository.save(carrinho);
    }

    public CarrinhoDTO montarCarrinhoDTO(Carrinho carrinho) {
        List<ItemCarrinhoDTO> itens = carrinho.getItens().stream()
                .map(item -> new ItemCarrinhoDTO(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getProduto().getPreco(),
                        item.getQuantidade()
                ))
                .toList();

        return new CarrinhoDTO(carrinho.getId(), itens);
    }
}
