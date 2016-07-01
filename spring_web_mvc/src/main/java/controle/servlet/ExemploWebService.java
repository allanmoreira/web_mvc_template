package controle.servlet;

import com.google.gson.Gson;
import negocio.exception.PessoaDAOException;
import org.springframework.web.bind.annotation.RestController;
import persistencia.dao.PessoaDAOMySQL;
import persistencia.entity.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by allanmoreira on 28/06/16.
 */

/**
 * Reparar que essa Servlet é assinada como RestController, e não como Controller, como a Servlet.
 * Essa servlet age como um Web Service com REST.
 */
@RestController
public class ExemploWebService {

    @Autowired
    PessoaDAOMySQL pessoaDAOMySQL;

    @RequestMapping(value = "teste_rest", method= RequestMethod.GET)
    public String home() throws PessoaDAOException {
        List<Pessoa> listaPessoas;
        listaPessoas = pessoaDAOMySQL.selectAll();
        return new Gson().toJson(listaPessoas);
    }
}
