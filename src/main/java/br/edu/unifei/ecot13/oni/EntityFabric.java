package br.edu.unifei.ecot13.oni;

public interface EntityFabric<C extends Entity> {
	public C makeEntity();
}
