package br.edu.unifei.ecot13.oni;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class DuplicantBuilder {
	protected Random rand = new Random(); 

	public void build(Duplicant d) {
		buildInterests(d);
		buildTraits(d);
		buildStressReaction(d);
		buildOverjoyedResponse(d);
		buildAbout(d);
	}

	public abstract void buildInterests(Duplicant d);

	public void buildTraits(Duplicant d) {
		int amount;
		int s = rand.nextInt(100);
		if (s > 80) amount = 3;
		else if (s > 50) amount = 2;
		else amount = 1;
		
		List<PositiveTraitEnum> pt = Arrays.asList(PositiveTraitEnum.values().clone());
		Collections.shuffle(pt, rand);
		for (int i = 0; i < amount; i++) {
			d.getTraits().add(new PositiveTrait(pt.get(i)));
		}
		
		s = rand.nextInt(100);
		if (s > 80) amount = 3;
		else if (s > 50) amount = 2;
		else amount = 1;
		
		List<NegativeTraitEnum> nt = Arrays.asList(NegativeTraitEnum.values().clone());
		Collections.shuffle(nt, rand);
		for (int i = 0; i < amount; i++) {
			d.getTraits().add(new NegativeTrait(nt.get(i)));
		}
	}
	
	public void buildStressReaction(Duplicant d) {
		int s = rand.nextInt(StressReactionEnum.values().length);
		d.setStressReaction(StressReactionEnum.values()[s]);
	}
	
	public void buildOverjoyedResponse(Duplicant d) {
		int s = rand.nextInt(OverjoyedResponseEnum.values().length);
		d.setOverjoyedResponse(OverjoyedResponseEnum.values()[s]);
	}
	
	public void buildAbout(Duplicant d) {
		String names[] = {"Mima", "Ashkan", "Hassan", "Jean", "Nikola", "Harold"};
		String description[] = {"Get a bunch of %1$ss together in a room, and you'll have... a bunch of %1$ss together in a room.",
				"This %1$s once claimed he could build a laser so powerful it would rip the colony in half. No one asked him to prove it.",
				"Just because %1$ss are a little slow doesn't mean they can't suffer from soul-crushing existential crises."};
		int s = rand.nextInt(names.length);
		d.setName(names[s]);
		s = rand.nextInt(description.length);
		d.setAbout(String.format(description[s], d.getName()));
	}	
}
