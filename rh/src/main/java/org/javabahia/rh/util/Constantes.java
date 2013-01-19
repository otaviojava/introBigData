
package org.javabahia.rh.util;

import org.apache.lucene.util.Version;

/**
 *
 * @author otavio
 */
public class Constantes {


  private static final String DIRECTORY_INDEX = "/index/";

  private static final String rootFolder="/lucene/";
  
  
//INDICES para a pesquisa no lucene  
public static final String ESTADO_INDICE = "Estado";
public static final String ID_INDICE = "Indice";
public static final String TUDO = "TUDO";


public static Version getVersion(){
return  Version.LUCENE_36;

}

  public static String getIndexDirectory() {
    return getRootFolder() + DIRECTORY_INDEX;
  }

  /**
   * @return the rootFolder
   */
  public static String getRootFolder() {
    return  System.getProperty("user.home")+rootFolder;
  }


    
}
