package Loja_Virtual.DTOS;

import java.time.LocalDateTime;
import java.util.List;

public record DescontoEmLoteRequestDTO(
        List<Long> produtosIds,
        Double percentual,
        LocalDateTime inicio,
        LocalDateTime fim
) {}
