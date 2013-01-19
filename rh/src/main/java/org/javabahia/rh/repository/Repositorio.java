package org.javabahia.rh.repository;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.javabahia.rh.model.Pesquisa;
import org.javabahia.rh.model.Pessoa;
import org.javabahia.rh.repository.cache.CacheService;
import org.javabahia.rh.validador.PessoaValidador;

@RequestScoped
public class Repositorio implements Serializable{

	

	private static final long serialVersionUID = 6348051462358240853L;
	
	@Inject
	private CacheService cacheService;
	
	@Inject
	private PessoaValidador pessoaValidador;
	
	public List<Pessoa> buscar(Pesquisa pesquisa){
		
		return cacheService.pesquisar(pesquisa);
	}
	
	public void salvar(Pessoa pessoa){
		pessoaValidador.salvar(pessoa);
	}
}
