package br.edu.unifei.ecot13.oni;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CookerDuplicant extends DuplicantBuilder {

	@Override
	public void buildInterests(Duplicant d) {
		AtributeEnum specificity = AtributeEnum.CUISINE;
		int amount = rand.nextInt(1, 4);
		Interest interest;
		List<AtributeEnum> atts = Arrays.asList(AtributeEnum.values().clone());
		Collections.shuffle(atts);
		
		for (int i = 0; i < amount; i++) {
			interest = new Interest();
			AtributeEnum a;
			
			if (i == 0) 
				a = specificity;
			else
				if (atts.get(i).equals(specificity))
					a = atts.get(i+1);
				else
					a = atts.get(i);
					
			interest.setType(a);
			interest.setBonus(rand.nextInt(1, 15/amount));
			d.getInterests().add(interest);
		}
	}

}
