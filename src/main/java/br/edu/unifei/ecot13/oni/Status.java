package br.edu.unifei.ecot13.oni;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Status {
	@Id
	@GeneratedValue
	private int codigo;
	private String description;
	
}
