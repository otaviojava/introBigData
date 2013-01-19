package br.com.tdc.repository.dao;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.easycassandra.persistence.Persistence;

/**
 *
 * @author otavio
 */
@RequestScoped
public class PersistenceCassandra {
    @Inject    
    private Persistence persistence;
    
    @SuppressWarnings("rawtypes")
    public List listar( Class baseClass){
    return persistence.findAll(baseClass);
    }
    @SuppressWarnings("rawtypes")
	public List listarPeloIndice(Class baseClass,Object indice){
    return persistence.findByIndex(indice, baseClass);
    }
    
    public boolean atualizar(Object bean){
    return persistence.update(bean);
    }
    
    
    @SuppressWarnings("rawtypes")
	public Object recuperar(Object chave,Class baseClass){
    return persistence.findByKey(chave, baseClass);
    }
    public boolean criar(Object bean){
    return persistence.insert(bean);
    }
    
    public boolean  remover(Object bean){
    return persistence.delete(bean);
    }
    @SuppressWarnings("rawtypes")
    public boolean  removerPelaChave(Object chave,Class baseClass){
    return persistence.deleteByKeyValue(chave, baseClass);
    }
    
    
}