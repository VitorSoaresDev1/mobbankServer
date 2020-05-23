package br.com.mobbank.mobBankServer.repository;

import br.com.mobbank.mobBankServer.model.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoMovimentacaoRepository extends JpaRepository<TipoMovimentacao, Long> {
}