package br.com.mobbank.mobBankServer.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mobbank.mobBankServer.controller.dto.CardDto;
import br.com.mobbank.mobBankServer.controller.form.UpdateCardForm;
import br.com.mobbank.mobBankServer.controller.form.CardForm;
import br.com.mobbank.mobBankServer.model.Card;
import br.com.mobbank.mobBankServer.repository.CardRepository;
import br.com.mobbank.mobBankServer.repository.UserRepository;

@RestController
@RequestMapping("/cards")
public class CardController {

	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<CardDto> lista() {
		List<Card> cards = cardRepository.findAll();
		return CardDto.converter(cards);			
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CardDto> cadastrar(@RequestBody @Valid CardForm form, UriComponentsBuilder uriBuilder) {
		Card card = null;
		try {
			card = form.converter(userRepository);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cardRepository.save(card);
		URI uri = uriBuilder.path("/cards/{id}").buildAndExpand(card.getId()).toUri();
		return ResponseEntity.created(uri).body(new CardDto(card));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<CardDto>> detalhar(@PathVariable Long id) {
		Optional<List<Card>> cards = cardRepository.findByUser_id(id);
		if(cards.isPresent()) {
			
			return ResponseEntity.ok(CardDto.converter(cards.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CardDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateCardForm form){
		Optional<Card> optional = cardRepository.findById(id);
		if(optional.isPresent()) {
			Card card = form.atualizar(id, cardRepository);
			return ResponseEntity.ok(new CardDto(card));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Card> optional = cardRepository.findById(id);
		if(optional.isPresent()) {
			cardRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
