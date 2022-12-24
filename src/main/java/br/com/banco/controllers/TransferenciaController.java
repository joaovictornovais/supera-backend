package br.com.banco.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transferencias")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class TransferenciaController {
	
	private final TransferenciaService service;
	
	@GetMapping
	public ResponseEntity<List<Transferencia>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<Transferencia> transferenciaOptional = service.findById(id);
		if (transferenciaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transferencia com ID " + id + " não encontrada.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(transferenciaOptional.get());
	}
	
	@PostMapping
	public ResponseEntity<Transferencia> create(@RequestBody Transferencia novaTransferencia) {
		novaTransferencia.setDataTransferencia(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(novaTransferencia));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Transferencia novaTransferencia) {
		Optional<Transferencia> transferenciaOptional = service.findById(id);
		if (transferenciaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transferencia com ID " + id + " não encontrada.");
		}
		var transferenciaModel = transferenciaOptional.get();
		transferenciaModel.setValor(novaTransferencia.getValor());
		transferenciaModel.setTipo(novaTransferencia.getTipo());
		transferenciaModel.setNomeOperadorTransacao(novaTransferencia.getNomeOperadorTransacao());
		transferenciaModel.setConta_id(novaTransferencia.getConta_id());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(transferenciaModel));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Optional<Transferencia> transferenciaOptional = service.findById(id);
		if (transferenciaOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transferencia com ID " + id + " não encontrada.");
		}
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Transferencia deletada com sucesso.");
	}

}
