package org.javabahia.rh.bean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.javabahia.rh.model.Endereco;
import org.javabahia.rh.model.Pesquisa;
import org.javabahia.rh.model.Pessoa;
import org.javabahia.rh.model.TipoPesquisa;
import org.javabahia.rh.repository.Repositorio;

/**
 *
 * @author otaviojava
 */
@Named
public class PessoaBean extends  AbstractBean{
    
	private static final long serialVersionUID = -6986391447873033015L;
	
	@Inject
	private Repositorio repositorio;
	
	
    /**
     * apenas uma chamada para uma demonstração
     * @return
     */
    public String getNome(){
    Pessoa pessoa=new Pessoa();
    pessoa.setCurriculo("Otávio");
    pessoa.setEndereco(new Endereco());
    pessoa.setNickName("karen");
    pessoa.setNome("Otávio");
    pessoa.setEndereco(new Endereco());
    pessoa.getEndereco().setCidade("Salvador");
    pessoa.getEndereco().setEstado("Bahia");
    repositorio.salvar(pessoa);
    Pesquisa pesquisa=new Pesquisa();
    pesquisa.setCampoPesquisa("karen");
    pesquisa.setTipoPesquisa(TipoPesquisa.NICKNAME);
    List<Pessoa> pessoas=repositorio.buscar(pesquisa);
    return pessoa.getNome();
    
    
    }
   
}
