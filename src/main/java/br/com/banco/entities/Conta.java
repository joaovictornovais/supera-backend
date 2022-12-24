package br.com.banco.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id_conta;
	
	@Column(nullable = false, length = 50)
	private String nome_responsavel;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conta_id")
	private List<Transferencia> transferencias;
	
}
