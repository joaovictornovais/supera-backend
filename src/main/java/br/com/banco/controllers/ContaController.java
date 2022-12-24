package br.com.banco.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Conta;
import br.com.banco.services.ContaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/contas")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ContaController {
	
	private final ContaService service;
	
	@GetMapping
	public ResponseEntity<List<Conta>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<Conta> contaOptional = service.findById(id);
		if (contaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta com ID " + id + " não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(contaOptional.get());
	}
	
	@PostMapping
	public ResponseEntity<Conta> create(@RequestBody Conta novaConta) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(novaConta));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Conta novaConta) {
		Optional<Conta> contaOptional = service.findById(id);
		if (contaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta com ID " + id + " não encontrada.");
		}
		var contaModel = contaOptional.get();
		contaModel.setNome_responsavel(novaConta.getNome_responsavel());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(contaModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Optional<Conta> contaOptional = service.findById(id);
		if (contaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta com ID " + id + " não encontrada.");
		}
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso.");
	}
	
	
	
}
