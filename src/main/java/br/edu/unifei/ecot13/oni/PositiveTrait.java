package br.edu.unifei.ecot13.oni;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PositiveTrait extends Trait {

	public PositiveTrait(PositiveTraitEnum type) {
		super(type);
	}

	public PositiveTrait() {}
}
