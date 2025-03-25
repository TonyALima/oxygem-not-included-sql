package br.edu.unifei.ecot13.oni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@javax.persistence.Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Duplicant extends Entity{

	private static final long serialVersionUID = 781589458749458150L;
	private String name;
	private String about;
	@OneToOne
	private Humor humor = new Normal();
	@OneToMany
	private Map<AtributeEnum, Atribute> atributes = new HashMap<AtributeEnum, Atribute>();
	@OneToMany
	private List<Trait> traits = new ArrayList<Trait>();
	@OneToMany
	private Map<DuplicantConditionEnum, DuplicantCondition> conditions = new HashMap<DuplicantConditionEnum, DuplicantCondition>();
	@OneToMany
	private List<Interest> interests = new ArrayList<Interest>();
	private OverjoyedResponseEnum overjoyedResponse;
	private StressReactionEnum stressReaction;

	protected Duplicant() {}
}
