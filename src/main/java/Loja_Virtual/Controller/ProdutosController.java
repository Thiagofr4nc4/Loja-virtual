package Loja_Virtual.Controller;

import Loja_Virtual.DTOS.AdicionarProdutoDTO;
import Loja_Virtual.DTOS.CarrinhoDTO;
import Loja_Virtual.Entities.Carrinho;
import Loja_Virtual.Repository.ProdutosRepository;
import Loja_Virtual.DTOS.ProdutoRequestDTO;
import Loja_Virtual.Entities.Produto;
import Loja_Virtual.Services.CarrinhoService;
import Loja_Virtual.Services.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/loja-virtual")
public class ProdutosController {

    private final ProdutosService produtosService;
    private final CarrinhoService carrinhoService;

    @GetMapping("/produtos")
    public List<Produto> listarProdutos(){return produtosService.listarProdutos();}

    @PostMapping("/admin/produto")
    public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoRequestDTO dto){
        Produto salvo = produtosService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PatchMapping("/admin/produto/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto){
        Produto atualizado = produtosService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/admin/{id}")
        public ResponseEntity<Produto> deletar(@PathVariable Long id){
            Produto deletado = produtosService.deletar(id);
            return ResponseEntity.noContent().build();

    }

    @PostMapping("/adicionar-carrinho/{usuarioId}")
    public ResponseEntity<Carrinho> adicionarCarrinho(@PathVariable Long usuarioId,
                                                      @RequestBody AdicionarProdutoDTO dto){
        Carrinho carrinho = carrinhoService.adicionarProduto
                (       usuarioId,
                        dto.produtoId(),
                        dto.quantidade()
                );

        CarrinhoDTO carrinhoDTO = carrinhoService.montarCarrinhoDTO(carrinho);
        return ResponseEntity.ok(carrinho);
    }
}