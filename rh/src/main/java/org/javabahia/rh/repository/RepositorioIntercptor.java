package org.javabahia.rh.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.javabahia.rh.model.Pesquisa;
import org.javabahia.rh.model.Pessoa;
import org.javabahia.rh.repository.cassandra.PessoaCassandra;
import org.javabahia.rh.repository.lucene.LuceneService;

@Leitura
@Interceptor
public class RepositorioIntercptor {
	
	@Inject
	private Ehcache cache;
	
	@Inject
	private LuceneService luceneService;
	
	@Inject
	private PessoaCassandra pessoaCassandra;
	
	  @AroundInvoke
      public Object pesquisar(InvocationContext ctx) throws Exception {
            
            @SuppressWarnings("unchecked")
			List<Pessoa> resultado = (List<Pessoa>) ctx.proceed();
            if(resultado==null){
             return esquentarCache(getPesquisa(ctx.getParameters()));	
            }
            
            
            return resultado;
      }
	  
	  
	  private List<Pessoa> esquentarCache(Pesquisa pesquisa) {
		
		     List<Pessoa> pessoas=new ArrayList<Pessoa>();
		        
		        
		        if(pesquisa.isTextoVazio()){
		            pessoas= pessoaCassandra.listar();
		            
		        }
		        
		        switch (pesquisa.getTipoPesquisa()){
		        case NICKNAME:
		           Pessoa pessoa=pessoaCassandra.recuperar(pesquisa.getCampoPesquisa()); 
		            if(pessoa!=null){
		            pessoas.add(pessoa);
		            }
		            break;
		        case PROFISSAO:
		            pessoas=pessoaCassandra.recuperarPeloIndice(pesquisa.getCampoPesquisa());
		            break;
		            case REGIAO:
		                pessoas=luceneService.findByEstado(pesquisa.getCampoPesquisa());
		                break;
		                case TUDO:
		                pessoas=luceneService.findByTudo(pesquisa.getCampoPesquisa());
		                break;
		        }
		        
		     
		        inserir(pesquisa, pessoas);
		        return pessoas;
	}


	private Pesquisa getPesquisa(Object[] resultado){
		  return (Pesquisa)resultado[0];
	  }


	  private void inserir(Pesquisa pesquisa, List<Pessoa> pessoas)
	  {
	      
	    
		  cache.put(new Element(pesquisa, pessoas));
	  }
}
