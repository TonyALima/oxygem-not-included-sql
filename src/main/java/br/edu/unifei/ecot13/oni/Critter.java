package br.edu.unifei.ecot13.oni;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@javax.persistence.Entity
@EqualsAndHashCode(callSuper = true)
public class Critter extends Entity {
	private static final long serialVersionUID = -1236754570614596180L;
	private String name;
	@OneToMany
	private Map<CritterConditionEnum ,CritterCondition> conditions = new HashMap<CritterConditionEnum ,CritterCondition>();
	
	protected Critter() {}
	
}
