package Loja_Virtual.Controller;

import Loja_Virtual.Entities.Produto;
import Loja_Virtual.Services.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/loja-virtual")
public class ProdutosController {

    private final ProdutosService produtosService;

    @GetMapping
    public List<Produto> listarProdutos(){return produtosService.listarProdutos();}
}