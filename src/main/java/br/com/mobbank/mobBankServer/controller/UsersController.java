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

import br.com.mobbank.mobBankServer.controller.dto.UserDto;
import br.com.mobbank.mobBankServer.controller.form.UpdateUserForm;
import br.com.mobbank.mobBankServer.controller.form.UserForm;
import br.com.mobbank.mobBankServer.model.User;
import br.com.mobbank.mobBankServer.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<UserDto> lista() {
		List<User> users = userRepository.findAll();
		return UserDto.converter(users);			
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> cadastrar(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User usuario = form.converter();
		userRepository.save(usuario);
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(usuario));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> detalhar(@PathVariable Long id) {
		Optional<User> usuario = userRepository.findById(id);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(new UserDto(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("byemail/{email}")
	public ResponseEntity<UserDto> detalhar(@PathVariable String email) {
		Optional<User> usuario = userRepository.findByEmail(email);
		if(usuario.isPresent()) {
			return ResponseEntity.ok(new UserDto(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateUserForm form){
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			User usuario = form.atualizar(id, userRepository);
			return ResponseEntity.ok(new UserDto(usuario));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
