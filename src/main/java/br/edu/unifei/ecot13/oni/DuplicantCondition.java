package br.edu.unifei.ecot13.oni;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class DuplicantCondition extends EntityCondition {

	@ManyToOne
	private Duplicant duplicant;
	@Transient
	private List<ConditionObserver> observers = new ArrayList<ConditionObserver>();
	
	public DuplicantCondition(DuplicantConditionEnum type, Duplicant d) {
		super(type);
		if (this.getType() == DuplicantConditionEnum.STRESS)
			observers.add( new StressObserver());
		if (this.getType() == DuplicantConditionEnum.HEALTH)
			observers.add( new HealthObserver());
		this.duplicant = d;
	}
	
	public DuplicantCondition() {}
		
	private void notify(double v) {
		for (ConditionObserver o : observers) {
			o.update(duplicant, v);
		}
	}
	
	public void setValue(double value) {
		super.setValue(value);
		notify(value);
	}
}
