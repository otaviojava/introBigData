
package br.com.tdc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.tdc.entity.Pessoa;
import br.com.tdc.entity.TipoPesquisa;
import br.com.tdc.repository.lucene.LuceneService;
import br.com.tdc.service.PessoaService;

/**
 *
 * @author otavio
 */
@ManagedBean
@ViewScoped
public class BuscaBean extends AbstractBean{
    
	private static final long serialVersionUID = -7054587367111040618L;

	private String campoPesquisa;
        
        @Inject
        private LuceneService luceneService;
    
        private TipoPesquisa tipoPesquisa;
        
    @Inject
    private PessoaService pessoaService;
    
    private List<Pessoa> pessoas;
    
    public void pesquisar(){
        if(campoPesquisa==null||campoPesquisa.length()==0){
            pessoas= pessoaService.listar();
            return;
        }
        pessoas=new ArrayList<Pessoa>();
        switch (tipoPesquisa){
        case NICKNAME:
           Pessoa pessoa=pessoaService.recuperar(campoPesquisa); 
            if(pessoa!=null){
            pessoas.add(pessoa);
            }
            break;
        case PROFISSAO:
            pessoas=pessoaService.recuperarPeloIndice(campoPesquisa);
            break;
            case REGIAO:
                pessoas=luceneService.findByEstado(campoPesquisa);
                break;
                case TUDO:
                pessoas=luceneService.findByTudo(campoPesquisa);
                break;
        }
        
       verificarTabelaVazia(pessoas);
        
    }
    

    @Inject
    public void init(){
        
        tipoPesquisa=TipoPesquisa.NICKNAME;
    }
    public String getCampoPesquisa() {
        return campoPesquisa;
    }

    public void setCampoPesquisa(String campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
    }

    public TipoPesquisa getTipoPesquisa() {
        return tipoPesquisa;
    }

    public void setTipoPesquisa(TipoPesquisa tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    
    
    
    
}
