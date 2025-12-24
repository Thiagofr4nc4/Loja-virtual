package Loja_Virtual.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loja-virtual")
public class ProdutosController {

    @GetMapping
    public String hello(){
        return "Loja vende tudo barato";
    }
}
