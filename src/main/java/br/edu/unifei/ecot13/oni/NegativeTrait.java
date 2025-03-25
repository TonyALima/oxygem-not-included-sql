package br.edu.unifei.ecot13.oni;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class NegativeTrait extends Trait {

	public NegativeTrait(NegativeTraitEnum type) {
		super(type);
	}
	
	public NegativeTrait() {}
}
