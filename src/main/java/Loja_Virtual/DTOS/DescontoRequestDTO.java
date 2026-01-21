package Loja_Virtual.DTOS;

import java.time.LocalDateTime;

public record DescontoRequestDTO(
        Double percentual,
        LocalDateTime inicio,
        LocalDateTime fim
) {}
