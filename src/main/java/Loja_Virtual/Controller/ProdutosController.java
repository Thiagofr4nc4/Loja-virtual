package Loja_Virtual.Controller;

import Loja_Virtual.Controller.Repository.ProdutosRepository;
import Loja_Virtual.DTOS.ProdutoRequestDTO;
import Loja_Virtual.Entities.Produto;
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
    private final ProdutosRepository produtosRepository;

    @GetMapping("/produtos")
    public List<Produto> listarProdutos(){return produtosService.listarProdutos();}

    @PostMapping("/criar-produto")
    public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoRequestDTO dto){
        Produto salvo = produtosService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PatchMapping("/editar-produto/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto){
        Produto atualizado = produtosService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("{id}")
        public ResponseEntity<Produto> deletar(@PathVariable Long id){
            Produto deletado = produtosService.deletar(id);
            return ResponseEntity.noContent().build();

    }

}