package org.javabahia.rh.resource;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.javabahia.rh.model.Pesquisa;
import org.javabahia.rh.model.Pessoa;
import org.javabahia.rh.model.TipoPesquisa;
import org.javabahia.rh.repository.Repositorio;




@Path("/pessoa")
@RequestScoped
public class MessageMessageRestService {
	
	@Inject
	private Repositorio repositorio;
	
	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa getId(@PathParam("param") String chave) {
		Pesquisa pesquisa=new Pesquisa();
		pesquisa.setCampoPesquisa(chave);
		pesquisa.setTipoPesquisa(TipoPesquisa.NICKNAME);
		
		List<Pessoa> pessoas=repositorio.buscar(pesquisa);
		if(pessoas!=null&& !pessoas.isEmpty()){
			return pessoas.get(0);
		}
		return new Pessoa();
 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String salvar(Pessoa pessoa){
		repositorio.salvar(pessoa);
		
		return "Pessoa salva com sucesso!!";
	}
	
	@GET
	public String getStatus(){
		return "funcionando!";
	}
}
