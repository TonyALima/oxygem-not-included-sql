package br.edu.unifei.ecot13.oni;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomDuplicant extends DuplicantBuilder{

	@Override
	public void buildInterests(Duplicant d) {
		int amount = rand.nextInt(1, 4);
		Interest interest;
		List<AtributeEnum> atts = Arrays.asList(AtributeEnum.values().clone());
		Collections.shuffle(atts, rand);
		for (int i = 0; i < amount; i++) {
			interest = new Interest();
			AtributeEnum a;
			a = atts.get(i);
			interest.setType(a);
			interest.setBonus(rand.nextInt(1, 15/amount));
			d.getInterests().add(interest);
		}
	}

}
