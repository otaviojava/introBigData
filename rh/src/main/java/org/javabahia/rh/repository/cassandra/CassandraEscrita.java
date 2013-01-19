package org.javabahia.rh.repository.cassandra;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import org.javabahia.rh.model.Pessoa;
import org.javabahia.rh.repository.Escrita;

/**
 *
 * @author otaviojava
 */

@Decorator
public class CassandraEscrita     implements  Escrita{
    @Inject
    @Delegate
    private Escrita escrita;

    @Inject
    private CassandraService cassandraService;
    @Override
    public Pessoa salvar(Pessoa pessoa) {
    	
    	
    	cassandraService.criar(escrita.salvar(pessoa));
    	Logger.getLogger(CassandraEscrita.class.getName()).log(Level.INFO,"Passando pelo Cassandra");
    	return pessoa;
    	 
    }
    
}
