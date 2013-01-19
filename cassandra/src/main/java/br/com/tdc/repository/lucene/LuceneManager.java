package br.com.tdc.repository.lucene;

import br.com.tdc.util.Constantes;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

/**
 *
 * @author otavio
 */
@ApplicationScoped
public class LuceneManager implements Serializable{

	private static final long serialVersionUID = -8280220793266559394L;
	
		@Produces
        private Directory directory;
    

    @Inject
    public void init() {
        directory = new RAMDirectory();
        try {
            levantarServico();
        } catch (IOException e) {
        	Logger.getLogger(LuceneManager.class.getName()).log(Level.SEVERE,
                    null, e);
        }
    }

    public void levantarServico() throws IOException {
        Directory disco = FSDirectory.open(new File(Constantes.getIndexDirectory()));
        backup(disco, directory);
    }

    public void backup(Directory deDiretorio, Directory paraDiretoria) throws IOException {

        for (String file : deDiretorio.listAll()) {
            deDiretorio.copy(paraDiretoria, file, file); // newFile can be either file, or a new name
        }
    }

}
