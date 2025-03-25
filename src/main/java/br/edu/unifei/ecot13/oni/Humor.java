package br.edu.unifei.ecot13.oni;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public abstract class Humor {
	@Id
	@GeneratedValue
	private int codigo;
	
	public void changeHumor(Duplicant d, double stress) {};
}
