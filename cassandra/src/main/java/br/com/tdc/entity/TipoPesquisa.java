package br.com.tdc.entity;

/**
 *
 * @author otavio
 */
public enum TipoPesquisa {
 NICKNAME("Nick Name"),PROFISSAO("Profissão"),REGIAO("Região"),TUDO("Tudo");
 
 private String nome;
 
 TipoPesquisa(String nome){
 this.nome=nome;
 }
 
 public String getNome(){
      return nome;
 }
}
