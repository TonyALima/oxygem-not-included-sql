package br.edu.unifei.ecot13.oni;

public class DuplicantFabric implements EntityFabric<Duplicant> {

	@Override
	public Duplicant makeEntity() {
		Duplicant d = new Duplicant();
		for (DuplicantConditionEnum dc : DuplicantConditionEnum.values()) {
			d.getConditions().put(dc, new DuplicantCondition(dc, d));
		}
		
		for (AtributeEnum a: AtributeEnum.values()) {
			d.getAtributes().put(a, new Atribute(a));
		}

		return d;
	}

}
