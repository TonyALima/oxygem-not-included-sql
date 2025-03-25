package br.edu.unifei.ecot13.oni;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@javax.persistence.Entity
public abstract class Entity implements Serializable{
	private static final long serialVersionUID = 8271543939336462001L;
	@Id
	@GeneratedValue
	private int codigo;
	@OneToMany
	private List<Status> status = new ArrayList<Status>();
		
}
