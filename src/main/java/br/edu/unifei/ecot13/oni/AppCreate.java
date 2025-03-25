package br.edu.unifei.ecot13.oni;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppCreate {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("oniPU");
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
		
	}

}
