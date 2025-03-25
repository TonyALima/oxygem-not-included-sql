package br.edu.unifei.ecot13.oni;

public class HealthObserver implements ConditionObserver {

	@Override
	public void update(Duplicant d, double value) {
		if (value == 0) {
			Status s = new Status();
			s.setDescription("Dead");
			d.getStatus().add(s);
		}
	}

}
