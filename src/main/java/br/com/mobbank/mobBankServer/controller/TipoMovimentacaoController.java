package br.com.mobbank.mobBankServer.controller;

import br.com.mobbank.mobBankServer.controller.dto.CardDto;
import br.com.mobbank.mobBankServer.controller.dto.DepositDto;
import br.com.mobbank.mobBankServer.controller.form.DepositForm;
import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.Deposit;
import br.com.mobbank.mobBankServer.model.TipoMovimentacao;
import br.com.mobbank.mobBankServer.repository.CardRepository;
import br.com.mobbank.mobBankServer.repository.DepositRepository;
import br.com.mobbank.mobBankServer.repository.TipoMovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos")
public class TipoMovimentacaoController {

    @Autowired
    private TipoMovimentacaoRepository tipoMovimentacaoRepository;

    @GetMapping
    public List<TipoMovimentacao> listar() {
        return tipoMovimentacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMovimentacao> detalhar(@PathVariable int id) {
        Optional<TipoMovimentacao> tiposMovimentacao = tipoMovimentacaoRepository.findById((long) id);
        return tiposMovimentacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
        @Transactional
        public ResponseEntity<TipoMovimentacao> cadastrar(@RequestBody @Valid TipoMovimentacao form, UriComponentsBuilder uriBuilder) {
        tipoMovimentacaoRepository.save(form);
            URI uri = uriBuilder.path("/tipos/{id}").buildAndExpand(form.getId()).toUri();
            return ResponseEntity.created(uri).body(new TipoMovimentacao(form.getId(), form.getDescricao()));
        }
}
