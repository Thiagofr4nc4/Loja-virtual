package Loja_Virtual.DTOS;

public record ItemCarrinhoDTO(
        Long produtoId,
        String nome,
        Double preco,
        int quantidade
) {}
