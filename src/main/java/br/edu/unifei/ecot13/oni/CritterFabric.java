package br.edu.unifei.ecot13.oni;

public class CritterFabric implements EntityFabric<Critter> {

	@Override
	public Critter makeEntity() {
		Critter c = new Critter();
		
		for (CritterConditionEnum cc : CritterConditionEnum.values()) {
			c.getConditions().put(cc, new CritterCondition(cc));
		}
		
		return c;
	}
	
}
