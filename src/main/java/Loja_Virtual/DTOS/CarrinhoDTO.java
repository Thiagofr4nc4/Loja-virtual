package Loja_Virtual.DTOS;

import java.util.List;

public record CarrinhoDTO(
        Long id,
        List<ItemCarrinhoDTO> itens
) {}
