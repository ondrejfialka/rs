package cz.ucl.jee.storage.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.*;

@Dependent
public class Resources {

	// Alternative way
	// This creates Entity Manager managed by the container. As the instance is managed by the server, no closing is necessary.
	// see https://stackoverflow.com/questions/19431423/getting-a-reference-to-entitymanager-in-java-ee-applications-using-cdi
	@PersistenceContext
	private EntityManager em;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return em;
	}
}

