package br.edu.unifei.ecot13.oni;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public abstract class Trait {
	@Id
	@GeneratedValue
	private int codigo;
	private Enum<?> type; 
	private String effect;
	
	public Trait(Enum<?> type) {
		super();
		this.type = type;
	}
	
	public Trait() {
		
	}
	
}
