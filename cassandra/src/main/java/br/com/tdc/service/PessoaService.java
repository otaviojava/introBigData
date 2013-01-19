
package br.com.tdc.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.tdc.entity.Pessoa;
import br.com.tdc.repository.dao.PersistenceCassandra;
import br.com.tdc.repository.lucene.LuceneService;

/**
 *
 * @author otavio
 */
@Stateless
public class PessoaService implements Serializable{

	private static final long serialVersionUID = -8268564782415140839L;
	
	@Inject
    private PersistenceCassandra persistenceCassandra;
	
	@Inject
	private LuceneService luceneService;
    
    
    public void salvar(Pessoa pessoa){
    persistenceCassandra.criar(pessoa);
    luceneService.salvar(pessoa);
    }
   
    public Pessoa recuperar(String nickName){
    return (Pessoa) persistenceCassandra.recuperar(nickName, Pessoa.class);
    }
    
    @SuppressWarnings("unchecked")
	public List<Pessoa> recuperarPeloIndice(String profissao){
    
    return persistenceCassandra.listarPeloIndice(Pessoa.class,profissao);
    }
    
    @SuppressWarnings("unchecked")
	public List<Pessoa> listar(){
        return persistenceCassandra.listar(Pessoa.class);
    }
}
