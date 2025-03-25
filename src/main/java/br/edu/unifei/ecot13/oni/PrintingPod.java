package br.edu.unifei.ecot13.oni;

import java.io.Serializable;

import lombok.Data;

@Data
public class PrintingPod implements Serializable{
	private static final long serialVersionUID = 7740598719444719814L;
	private DuplicantFabric duplicantFabric = new DuplicantFabric();
	private DuplicantBuilder duplicantBuilder;
	
	private static PrintingPod instace = new PrintingPod();
	
	private PrintingPod() {}

	public Duplicant printDuplicant() {
		Duplicant d = duplicantFabric.makeEntity();
		duplicantBuilder.build(d);
		
		System.out.println(d.getName());
		System.out.println(d.getAbout());
		for (Interest i: d.getInterests()) {
			System.out.print(i.getType() + " +");
			System.out.println(i.getBonus());
		}
		for (Trait t: d.getTraits()) {
			if (t instanceof PositiveTrait)
				System.out.println("\u001B[32m" + t.getType() + "\u001B[0m");
			else
				System.out.println("\u001B[31m" + t.getType() + "\u001B[0m");
		}
		System.out.println(d.getOverjoyedResponse());
		System.out.println(d.getStressReaction());
		
		return d;
	}

	public static PrintingPod getInstace() {
		return instace;
	}
}
