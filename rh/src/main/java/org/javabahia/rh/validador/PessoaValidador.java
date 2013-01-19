
package org.javabahia.rh.validador;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.javabahia.rh.model.Pessoa;
import org.javabahia.rh.repository.Escrita;

/**
 *
 * @author otaviojava
 */
public class PessoaValidador implements  Escrita {

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        pessoa.setCurriculo(pessoa.getCurriculo().toLowerCase());
        Logger.getLogger(PessoaValidador.class.getName()).log(Level.INFO,"Passando pelo validador ");
        return pessoa;
    }
    
}
