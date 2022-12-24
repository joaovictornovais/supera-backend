package br.com.banco.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransferenciaService {

	private final TransferenciaRepository repository;
	
	@Transactional
	public Transferencia save(Transferencia conta) {
		return repository.save(conta);
	}
	
	public List<Transferencia> findAll() {
		return repository.findAll();
	}
	
	public Optional<Transferencia> findById(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	@Transactional
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
