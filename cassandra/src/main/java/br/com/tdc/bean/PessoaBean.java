
package br.com.tdc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.tdc.entity.Pessoa;
import br.com.tdc.service.PessoaService;

/**
 *
 * @author otavio
 */
@ManagedBean
@ViewScoped
public class PessoaBean extends AbstractBean{
    
	private static final long serialVersionUID = -4869252881935271196L;

	@Inject
    private Pessoa pessoa;
    
    @Inject
    private PessoaService pessoaService;
    
       
    
    public void adicionar(){
     if(pessoa.getEndereco().getCidade()==null||pessoa.getEndereco().getCidade().length()==0){
         pessoa.getEndereco().setCidade(null); 
     }
    pessoaService.salvar(pessoa);
 
    adicionarMensagemInfo("Pessoa cadastrada com sucesso!");
    pessoa=new Pessoa();
    }
    

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
    
}
