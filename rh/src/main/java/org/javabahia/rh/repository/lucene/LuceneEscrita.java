package org.javabahia.rh.repository.lucene;

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
public class LuceneEscrita implements  Escrita{
    @Inject
    @Delegate
    private Escrita escrita;

    @Inject
    private LuceneService luceneService;
    
    @Override
    public Pessoa salvar(Pessoa pessoa) {
    	
       luceneService.salvar(escrita.salvar(pessoa));
       Logger.getLogger(LuceneEscrita.class.getName()).log(Level.INFO,"Passando pelo lucene ");
       return pessoa;
    }
    
}
