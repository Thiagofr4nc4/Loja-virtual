package Loja_Virtual.Controller;

import Loja_Virtual.DTOS.*;
import Loja_Virtual.Entities.Carrinho;
import Loja_Virtual.Entities.Produto;
import Loja_Virtual.Services.CarrinhoService;
import Loja_Virtual.Services.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/carrinho/{usuarioId}")
    public ResponseEntity<Carrinho> adicionarCarrinho(@PathVariable Long usuarioId,
                                                      @RequestBody AdicionarProdutoDTO dto,
                                                      Authentication authentication){
        String email = authentication.getName();

        Carrinho carrinho = carrinhoService.adicionarProduto
                (       usuarioId,
                        dto.produtoId(),
                        dto.quantidade()
                );

        CarrinhoDTO carrinhoDTO = carrinhoService.montarCarrinhoDTO(carrinho);
        return ResponseEntity.ok(carrinho);
    }


    //FUNÇÕES ADMIN -----------------------------------------------------------------------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/produto")
    public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoRequestDTO dto){
        Produto salvo = produtosService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/admin/produto/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto){
        Produto atualizado = produtosService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/produto/{id}")
        public ResponseEntity<Produto> deletar(@PathVariable Long id){
            Produto deletado = produtosService.deletar(id);
            return ResponseEntity.ok(deletado);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/produto/desconto/{id}")
        public ResponseEntity<Produto> aplicarDesconto(@PathVariable Long id,
                                                       @RequestBody DescontoRequestDTO dto){
        Produto descontoAplicado = produtosService.aplicarDesconto(id, dto);
        return ResponseEntity.ok(descontoAplicado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/produto/desconto")
        public ResponseEntity<Void> aplicarDescontoEmLote(@RequestBody DescontoEmLoteRequestDTO dto){
        produtosService.aplicarDescontoEmLote(dto);
        return ResponseEntity.ok().build();
    }
}