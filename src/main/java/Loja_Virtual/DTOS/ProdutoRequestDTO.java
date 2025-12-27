package Loja_Virtual.DTOS;

public record ProdutoRequestDTO(
        String nome,
        String descricao,
        Double preco,
        Integer estoque) {}
