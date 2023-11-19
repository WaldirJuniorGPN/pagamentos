package br.com.food.pagamentos.controller;

import br.com.food.pagamentos.dto.request.DadosCadastroPagamento;
import br.com.food.pagamentos.dto.response.DadosDetalhamentoPagamento;
import br.com.food.pagamentos.model.Pagamento;
import br.com.food.pagamentos.repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@Valid @RequestBody DadosCadastroPagamento dados, UriComponentsBuilder uriComponentsBuilder){
        var pagamento = new Pagamento(dados);
        var uri = uriComponentsBuilder.path("pagamento/{id}").buildAndExpand(pagamento.getId()).toUri();
        this.pagamentoRepository.save(pagamento);
        return ResponseEntity.ok(new DadosDetalhamentoPagamento(pagamento));
    }
}
