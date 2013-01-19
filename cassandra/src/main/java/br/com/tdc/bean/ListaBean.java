package br.com.tdc.bean;

import br.com.tdc.entity.TipoPesquisa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;

/**
 *
 * @author otavio
 */
@Model
public class ListaBean implements Serializable{
    
    
	private static final long serialVersionUID = 4944341449723911372L;

	public List<SelectItem> getTipoPesquisas(){
    List<SelectItem> itens=new ArrayList<SelectItem>();
    
    for(TipoPesquisa tipoPesquisa:TipoPesquisa.values()){
        itens.add(new SelectItem(tipoPesquisa, tipoPesquisa.getNome()));
    }
    return itens;
    }
}
