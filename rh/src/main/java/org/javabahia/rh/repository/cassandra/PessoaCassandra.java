package org.javabahia.rh.repository.cassandra;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.javabahia.rh.model.Pessoa;

@RequestScoped
public class PessoaCassandra implements Serializable{

	private static final long serialVersionUID = -8268564782415140839L;
	
	@Inject
    private CassandraService persistenceCassandra;
	
	
    
    
    public void salvar(Pessoa pessoa){
    	persistenceCassandra.criar(pessoa);
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
