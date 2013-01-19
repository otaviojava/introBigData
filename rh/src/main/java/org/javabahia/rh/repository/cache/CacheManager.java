package org.javabahia.rh.repository.cache;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.CacheConfiguration;

@ApplicationScoped
public class CacheManager implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Produces
	private Ehcache ehcache;
	
	
	@Inject
	public void init(){
		 net.sf.ehcache.CacheManager cacheManager=net.sf.ehcache.CacheManager.getInstance();
		  CacheConfiguration configuration=new CacheConfiguration("soujava",1000);
		  configuration.setEternal(false);
		  configuration.setTimeToIdleSeconds(1000);
		  configuration.setTimeToLiveSeconds(10000);
	     ehcache=new Cache(configuration);
	     cacheManager.addCache(ehcache);
	}
	

}
