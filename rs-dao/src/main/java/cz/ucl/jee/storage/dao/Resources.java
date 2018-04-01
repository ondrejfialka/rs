package cz.ucl.jee.storage.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.*;

@ApplicationScoped
public class Resources {

	// The server creates EntityManagerFactory and Entity Manager is created manually
	// We also add a disposer method to close the instance, co we don't have to do it in the cdoe that use the EM.
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Produces
	@RequestScoped
	public EntityManager create() {
		return this.entityManagerFactory.createEntityManager();
	}

	public void dispose(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}

	// Alternative way
	// This creates Entity Manager managed by the container. As the instance is managed by the server, no closing is necessary.
	// see https://stackoverflow.com/questions/19431423/getting-a-reference-to-entitymanager-in-java-ee-applications-using-cdi
	/*@PersistenceContext
	private EntityManager em;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return em;
	}*/
}

