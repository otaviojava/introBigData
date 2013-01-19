package br.com.tdc.entity;

import java.io.Serializable;
import javax.persistence.Column;

/**
 *
 * @author otavio
 */
public class Endereco implements Serializable{
   
	private static final long serialVersionUID = -4715492113467179846L;

	@Column
    private String cidade;
    
    @Column
    private String estado;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
