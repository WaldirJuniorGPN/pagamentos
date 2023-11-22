package br.com.food.pagamentos.controller;

import br.com.food.pagamentos.dto.request.DadosAtualizacaoPagamento;
import br.com.food.pagamentos.dto.request.DadosCadastroPagamento;
import br.com.food.pagamentos.service.PagamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@Valid @RequestBody DadosCadastroPagamento dados, UriComponentsBuilder uriComponentsBuilder) {
        var pagamento = this.pagamentoService.criarPagamento(dados);
        var uri = uriComponentsBuilder.path("pagamento/{id}").buildAndExpand(pagamento.id()).toUri();
        return ResponseEntity.created(uri).body(pagamento);
    }

    @GetMapping
    public ResponseEntity listar(Pageable paginacao) {
        var page = this.pagamentoService.listarTodosPagamentos(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPagamento(@PathVariable Long id) {
        var pagamento = this.pagamentoService.listarPagamentoId(id);
        return ResponseEntity.ok(pagamento);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPagamento dados) {
        var pagamento = this.pagamentoService.atualizarPagamento(dados);
        return ResponseEntity.ok(pagamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        this.pagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
