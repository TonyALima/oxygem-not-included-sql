package br.edu.unifei.ecot13.oni;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public abstract class EntityCondition {
	@Id
	@GeneratedValue
	private int codigo;
	private Enum<?> type; 
	private double value;
	private double max;
	
	public EntityCondition(Enum<?> type) {
		super();
		this.type = type;
	}
	public EntityCondition() {}
}
