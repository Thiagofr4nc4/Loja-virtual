package Loja_Virtual.DTOS;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        Long carrinhoID
) {}
