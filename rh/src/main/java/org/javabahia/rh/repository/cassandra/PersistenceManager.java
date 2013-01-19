package org.javabahia.rh.repository.cassandra;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.easycassandra.persistence.EasyCassandraManager;
import org.easycassandra.persistence.Persistence;

/**
 *
 * @author otavio
 */
@ApplicationScoped
public class PersistenceManager implements Serializable{

    private static final long serialVersionUID = 7689914524830816292L;
    
    @SuppressWarnings("unused")
    @Produces
    private Persistence persistence;

    @Inject
    public void init() {
    	EasyCassandraManager.getClient("tdcsp2012", "localhost", 9160);
    	EasyCassandraManager.getClient("tdcsp2012", "node2", 9160);
    	EasyCassandraManager.getClient("tdcsp2012", "node3", 9160);
        persistence = EasyCassandraManager.getPersistenceRandom("tdcsp2012");
    }
}
