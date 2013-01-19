package br.com.tdc.service;

import java.io.File;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import br.com.tdc.repository.lucene.LuceneManager;
import br.com.tdc.util.Constantes;

@Singleton
public class ScheduleService implements Serializable{
	
	
	
	private static final long serialVersionUID = 6861736977399634371L;

	@Inject
	private Directory directory;
       
	
	@Inject
	private LuceneManager luceneManager;    
  
  @Schedule(minute = "*/1", hour = "*")
  public void reindex() {
   
    try {
    
    	 Directory disco = FSDirectory.open(new File(Constantes.getIndexDirectory()));
   	  luceneManager.backup(directory, disco);
      
    }
    catch (Exception e) {
    	Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE,
                null, e);
    }

  }
}
