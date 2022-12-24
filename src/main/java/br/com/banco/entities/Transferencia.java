package br.com.banco.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.banco.enums.Tipos;
import lombok.Data;

@Entity
@Data
public class Transferencia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dataTransferencia;
	
	@Column(nullable = false)
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private Tipos tipo;
	
	@Column(length = 50)
	private String nomeOperadorTransacao;
	
	@Column(nullable = false)
	private int conta_id;
	
	
	
	
}
