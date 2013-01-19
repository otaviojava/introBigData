package org.javabahia.rh.model;

import org.javabahia.rh.model.Endereco;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;

import org.easycassandra.annotations.Index;

/**
 *
 * @author otavio
 */
@MappedSuperclass
@XmlRootElement
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 4586706067919962881L;

	@Id
    private String nickName;
    
    @Column
    private String nome;
    
    @Column
    private String curriculo;
    
    @Column
    @Index
    private String profissao;
    
    @Embedded
    private Endereco endereco;
    


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

  
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa() {
        endereco=new Endereco();
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }
    
    
    
    
    
}
