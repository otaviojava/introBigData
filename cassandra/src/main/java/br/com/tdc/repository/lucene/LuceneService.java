
package br.com.tdc.repository.lucene;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import br.com.tdc.entity.Pessoa;
import br.com.tdc.repository.dao.PersistenceCassandra;
import br.com.tdc.util.Constantes;

/**
 *
 * @author otavio
 */
@RequestScoped
public class LuceneService implements Serializable{

    private static final long serialVersionUID = 6802809373705830973L;
    private Analyzer analyzer;
  
    @Inject
    private Directory directory;
    
    @Inject
    private PersistenceCassandra persistenceCassandra;
    

  @Inject
  public void init() {
    analyzer = new StandardAnalyzer(Constantes.getVersion());
  }


  public void salvar(Pessoa pessoa) {

    IndexWriterConfig indexConfig = new IndexWriterConfig(Constantes.getVersion(),
        analyzer);

    
      try(IndexWriter indexWriter = new IndexWriter(directory, indexConfig)) {
		
		 // Adiciona no indexador
	      indexWriter.addDocument(criarDocumento(pessoa));
	      // Fechar o indexador
	      indexWriter.commit();
	      indexWriter.close();
	} catch (IOException e) {
		Logger.getLogger(LuceneManager.class.getName()).log(Level.SEVERE, null, e);
	}
     

    

  }

  
  private Document criarDocumento(Pessoa pessoa) throws IOException {
    Document document = new Document();

    document.add(new Field(Constantes.ESTADO_INDICE,pessoa.getEndereco().getEstado(), Store.YES,
        Index.NOT_ANALYZED_NO_NORMS));
    document.add(new Field(Constantes.ID_INDICE,pessoa.getNickName(), Store.YES,
            Index.NOT_ANALYZED_NO_NORMS,TermVector.WITH_POSITIONS_OFFSETS));

    document.add(new Field(Constantes.TUDO, getConteudoCurriculo(pessoa), Store.NO,
        Index.ANALYZED));

    return document;
  }
  private String getConteudoCurriculo(Pessoa pessoa) {
      StringBuilder stringBuilder=new StringBuilder();
      stringBuilder.append(pessoa.getNome());
      stringBuilder.append(" \n ");
      stringBuilder.append(pessoa.getNickName());
      stringBuilder.append(" \n ");
      stringBuilder.append(pessoa.getEndereco().getEstado());
      stringBuilder.append(" \n ");
      stringBuilder.append(pessoa.getEndereco().getCidade());
      stringBuilder.append(" \n ");
      stringBuilder.append(pessoa.getCurriculo());
      return stringBuilder.toString();
  }
  
   public List<Pessoa> findByEstado(String estado) {
    Query query = new TermQuery(new Term(Constantes.ESTADO_INDICE, estado));
    return executeQuery(query, Constantes.ESTADO_INDICE);
  }
   
   
    public List<Pessoa> findByTudo(String campoPesquisa) {
       Query query = new TermQuery(new Term(Constantes.TUDO, campoPesquisa));
    return executeQuery(query, Constantes.TUDO);
    }
   
   private List<Pessoa> executeQuery(Query q, String field)
      
  {

    List<Pessoa> pessoas = new ArrayList<Pessoa>();

    try {
      int hitsPerPage = 30;

      IndexReader reader = IndexReader.open(directory);
      IndexSearcher searcher = new IndexSearcher(reader);
      
      TopDocs topDocs = searcher.search(q, hitsPerPage);
      ScoreDoc[] hits = topDocs.scoreDocs;
     
      for (int i = 0; i < hits.length; ++i) {
        
        int docId = hits[i].doc;
        
        Document d = searcher.doc(docId);
        String nickName=d.get(Constantes.ID_INDICE);
        Pessoa pessoa=(Pessoa)persistenceCassandra.recuperar(nickName, Pessoa.class);
        if(pessoa!=null){
        pessoas.add(pessoa);
        }
      }
    }
    catch (Exception exception) {
    Logger.getLogger(LuceneService.class.getName()).log(Level.SEVERE,
                null, exception);
    }
    

    return pessoas;
  }

  
  
}
