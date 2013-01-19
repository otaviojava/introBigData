package org.javabahia.rh.repository.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.javabahia.rh.model.Pesquisa;
import org.javabahia.rh.model.Pessoa;
import org.javabahia.rh.repository.Leitura;

/**
 *
 * @author otaviojava
 */
@RequestScoped
public class CacheService implements Serializable{
    
   
	private static final long serialVersionUID = 1L;

	@Inject
	private Ehcache ehcache;
    
    
    
    
    
@Leitura     
@SuppressWarnings("unchecked")
public List<Pessoa> pesquisar(Pesquisa pesquisa)
  {
	if(ehcache.get(pesquisa)!=null){
     return  (List<Pessoa>) ehcache.get(pesquisa).getValue();
	}
	else{
		return null;
	}
      
  }
  
  
  

    
    
}
