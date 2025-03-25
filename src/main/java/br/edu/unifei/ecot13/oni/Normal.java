package br.edu.unifei.ecot13.oni;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Normal extends Humor {

	@Override
	public void changeHumor(Duplicant d, double stress) {
		if (stress < 10)
			d.setHumor(new Overjoyed());
		else if (stress >= 90)
			d.setHumor(new Stressed());
	}

}
