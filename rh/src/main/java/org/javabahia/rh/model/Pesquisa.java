package org.javabahia.rh.model;

import java.io.Serializable;

/**
 *
 * @author otaviojava
 */
public class Pesquisa implements Serializable{

	private static final long serialVersionUID = -7694633094012977536L;

	private String campoPesquisa;
    
    private TipoPesquisa tipoPesquisa;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.campoPesquisa != null ? this.campoPesquisa.hashCode() : 0);
        hash = 37 * hash + (this.tipoPesquisa != null ? this.tipoPesquisa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pesquisa other = (Pesquisa) obj;
        if ((this.campoPesquisa == null) ? (other.campoPesquisa != null) : !this.campoPesquisa.equals(other.campoPesquisa)) {
            return false;
        }
        if (this.tipoPesquisa != other.tipoPesquisa) {
            return false;
        }
        return true;
    }

    public boolean isTextoVazio() {
     return   campoPesquisa==null||campoPesquisa.length()==0;
    }

    public Pesquisa() {
        tipoPesquisa=TipoPesquisa.NICKNAME;
    }
    
    
}
