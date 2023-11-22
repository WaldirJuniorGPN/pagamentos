package br.com.food.pagamentos.service;

import br.com.food.pagamentos.dto.request.DadosAtualizacaoPagamento;
import br.com.food.pagamentos.dto.request.DadosCadastroPagamento;
import br.com.food.pagamentos.dto.response.DadosDetalhamentoPagamento;
import br.com.food.pagamentos.model.Pagamento;
import br.com.food.pagamentos.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public DadosDetalhamentoPagamento criarPagamento(DadosCadastroPagamento dados) {
        var pagamento = new Pagamento(dados);
        this.pagamentoRepository.save(pagamento);
        return new DadosDetalhamentoPagamento(pagamento);
    }

    public Page<DadosDetalhamentoPagamento> listarTodosPagamentos(Pageable paginacao) {
        var page = this.pagamentoRepository.findAll(paginacao).map(DadosDetalhamentoPagamento::new);
        return page;
    }

    public DadosDetalhamentoPagamento listarPagamentoId(Long id) {
        var pagamento = this.pagamentoRepository.getReferenceById(id);
        return new DadosDetalhamentoPagamento(pagamento);
    }

    public DadosDetalhamentoPagamento atualizarPagamento(DadosAtualizacaoPagamento dados) {
        var pagamento = this.pagamentoRepository.getReferenceById(dados.id());
        pagamento.atualizarDados(dados);
        return new DadosDetalhamentoPagamento(pagamento);
    }

    public void deletar(Long id){
        this.pagamentoRepository.deleteById(id);
    }
}
