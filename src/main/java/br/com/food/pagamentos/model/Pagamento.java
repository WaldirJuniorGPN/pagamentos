package br.com.food.pagamentos.model;

import br.com.food.pagamentos.dto.request.DadosCadastroPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Long pedidoId;
    private Long formaDePagamentoId;

    public Pagamento(DadosCadastroPagamento dados) {
        this.valor = dados.valor();
        this.nome = dados.nome();
        this.numero = dados.numero();
        this.expiracao = dados.expiracao();
        this.codigo = dados.codigo();
        this.status = dados.status();
        this.pedidoId = dados.pedidoId();
        this.formaDePagamentoId = dados.formaDePagamentoId();
    }
}
