package br.com.mobbank.mobBankServer.controller;

import br.com.mobbank.mobBankServer.controller.dto.DepositDto;
import br.com.mobbank.mobBankServer.controller.form.DepositForm;
import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.model.Deposit;
import br.com.mobbank.mobBankServer.repository.CardRepository;
import br.com.mobbank.mobBankServer.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deposits")
public class DepositController {
	@Autowired
	private DepositRepository depositRepository;
	@Autowired
	private CardRepository cardRepository;
	
	@GetMapping
	public List<DepositDto> listar() {
		List<Deposit> deposits = depositRepository.findAll();
		return DepositDto.converter(deposits);			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<DepositDto>> detalhar(@PathVariable Long id) {
		Optional<List<Deposit>> deposits = depositRepository.findByCardId(Math.toIntExact(id));
		return deposits.map(depositList -> ResponseEntity.ok(DepositDto.converter(depositList))).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DepositDto> cadastrar(@RequestBody @Valid DepositForm form, UriComponentsBuilder uriBuilder) {
		Optional<Card> card = cardRepository.findById(Long.parseLong(Integer.toString(form.getCardId())));
		if(card.isPresent()){
			String userPass = card.get().getUser().getSenha();
			if (form.getSenha().equals(userPass)) {
				if(form.getTipo() == 1) {
					Deposit deposit = form.converter();
					double newSaldo = (card.get().getSaldo()) + (form.getValue());
					card.get().setSaldo(newSaldo);
					depositRepository.save(deposit);
					URI uri = uriBuilder.path("/deposits/{id}").buildAndExpand(deposit.getId()).toUri();
					return ResponseEntity.created(uri).body(new DepositDto(deposit));
				}else if(form.getTipo() == 2){
					Deposit deposit = form.converter();
					double newSaldo = (card.get().getSaldo()) - (form.getValue());
					card.get().setSaldo(newSaldo);
					depositRepository.save(deposit);
					URI uri = uriBuilder.path("/deposits/{id}").buildAndExpand(deposit.getId()).toUri();
					return ResponseEntity.created(uri).body(new DepositDto(deposit));
				}else if(form.getTipo() == 3){
					Optional<Card> destiny = cardRepository.findByNumeroConta(Integer.toString(form.getTransferTo()));
					if(destiny.isPresent()){
						Deposit deposit = form.converter();
						double newSaldo = (card.get().getSaldo()) - (form.getValue());
						double destinyNewSaldo = (destiny.get().getSaldo()) + (form.getValue());
						card.get().setSaldo(newSaldo);
						destiny.get().setSaldo(destinyNewSaldo);
						depositRepository.save(deposit);
						URI uri = uriBuilder.path("/deposits/{id}").buildAndExpand(deposit.getId()).toUri();
						return ResponseEntity.created(uri).body(new DepositDto(deposit));
					}else{
						return new ResponseEntity("Conta destino não encontrada",HttpStatus.NOT_FOUND);
					}
				}else{
					return ResponseEntity.badRequest().build();
				}
			} else {
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
			}
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
}
