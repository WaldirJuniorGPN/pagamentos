package br.com.food.pagamentos.dto.request;

import br.com.food.pagamentos.model.Status;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DadosCadastroPagamento(
        @NotNull
        @Positive
        BigDecimal valor,
        @NotBlank
        @Size(max = 100)
        String nome,
        @NotBlank
        @Size(max = 9)
        String numero,
        @NotBlank
        @Size(max = 7)
        String expiracao,
        @NotBlank
        @Size(min = 3, max = 3)
        String codigo,
        @NotNull
        @Pattern(regexp = "^(CRIADO|CONFIRMADO|CANCELADO)$")
        Status status,
        @NotNull
        Long pedidoId,
        @NotNull
        Long formaDePagamentoId

        ) {
}
