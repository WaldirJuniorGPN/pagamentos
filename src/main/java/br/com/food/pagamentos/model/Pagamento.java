package br.com.food.pagamentos.model;

import br.com.food.pagamentos.dto.request.DadosAtualizacaoPagamento;
import br.com.food.pagamentos.dto.request.DadosCadastroPagamento;
import jakarta.persistence.*;
import lombok.*;

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
        this.status = Status.CRIADO;
        this.pedidoId = dados.pedidoId();
        this.formaDePagamentoId = dados.formaDePagamentoId();
    }

    public void atualizarDados(DadosAtualizacaoPagamento dados) {
        if (dados.valor() != null) {
            this.valor = dados.valor();
        }
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.expiracao() != null) {
            this.expiracao = dados.expiracao();
        }
        if (dados.codigo() != null) {
            this.codigo = dados.codigo();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (dados.pedidoId() != null) {
            this.pedidoId = dados.pedidoId();
        }
        if (dados.formaDepagamentoId() != null) {
            this.formaDePagamentoId = dados.formaDepagamentoId();
        }
    }
}
