package br.edu.unifei.ecot13.oni;

public class StressObserver implements ConditionObserver {

	@Override
	public void update(Duplicant d, double value) {
		d.getHumor().changeHumor(d, value);
	}

	
}
