package br.com.mobbank.mobBankServer.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mobbank.mobBankServer.controller.dto.DepositDto;
import br.com.mobbank.mobBankServer.controller.form.DepositForm;
import br.com.mobbank.mobBankServer.model.Deposit;
import br.com.mobbank.mobBankServer.repository.DepositRepository;

@RestController
@RequestMapping("/deposits")
public class DepositController {
	@Autowired
	private DepositRepository depositRepository;
	
	@GetMapping
	public List<DepositDto> lista() {
		List<Deposit> deposits = depositRepository.findAll();
		return DepositDto.converter(deposits);			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<DepositDto>> detalhar(@PathVariable Long id) {
		Optional<List<Deposit>> deposits = depositRepository.findByOwner_id(id);
		if(deposits.isPresent()) {
			
			return ResponseEntity.ok(DepositDto.converter(deposits.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DepositDto> cadastrar(@RequestBody @Valid DepositForm form, UriComponentsBuilder uriBuilder) {
		if(form.getSenha() == form.getOwner().getSenha()) {
			Deposit deposit = form.converter();
			depositRepository.save(deposit);
			URI uri = uriBuilder.path("/deposits/{id}").buildAndExpand(deposit.getId()).toUri();
			return ResponseEntity.created(uri).body(new DepositDto(deposit));			
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
}
