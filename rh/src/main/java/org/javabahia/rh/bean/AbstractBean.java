package org.javabahia.rh.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author otavio
 */
public class AbstractBean implements Serializable{

	private static final long serialVersionUID = 3072304787442972774L;


		protected void adicionarMensagemAviso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
	}

	@SuppressWarnings("rawtypes")
	protected void verificarTabelaVazia(List tabela){
		if(tabela==null||tabela.isEmpty()){
			adicionarMensagemInfo("Nenhum Registro Encontrado");
			
		}
	}
	
	protected void adicionarMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
	}

	
	protected void adicionarMensagemInfo(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}
}
