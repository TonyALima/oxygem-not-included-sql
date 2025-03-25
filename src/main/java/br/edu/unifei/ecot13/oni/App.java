package br.edu.unifei.ecot13.oni;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oniPU");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		
		List<Duplicant> dups = new ArrayList<Duplicant>();
		PrintingPod print = PrintingPod.getInstace();
		
		print.setDuplicantBuilder(new ResearcherDuplicant());
		dups.add(print.printDuplicant());
		System.out.println();
		
		print.setDuplicantBuilder(new CookerDuplicant());
		dups.add(print.printDuplicant());
		System.out.println();
		
		print.setDuplicantBuilder(new RandomDuplicant());
		dups.add(print.printDuplicant());
		System.out.println();
		
		for (Duplicant d :dups) {
			for (AtributeEnum a: AtributeEnum.values()) {
				em.persist(d.getAtributes().get(a));
			}
			for (DuplicantConditionEnum a: DuplicantConditionEnum.values()) {
				em.persist(d.getConditions().get(a));
			}
			for (Trait t: d.getTraits()) {
				em.persist(t);
			}
			for (Interest i: d.getInterests()) {
				em.persist(i);
			}
			em.persist(d);
		}
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

}
