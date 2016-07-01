package controle.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import negocio.exception.PessoaDAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import persistencia.dao.PessoaDAOMySQL;
import persistencia.entity.Pessoa;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

/**
 *
 * @author allan
 */
@Controller
public class PessoaServlet {

    private static Logger logger = Logger.getLogger(PessoaServlet.class);
    private static final String REST_SERVICE_URI = "http://localhost:8080/SpringWebMVC";

    @Autowired
    private PessoaDAOMySQL pessoaDAOMySQL;

    /**
     * Faz uma requisição ao "ExemploWebService" e manda os dados para a página
     */
    @RequestMapping("lista_pessoas")
    public ModelAndView teste() {
        ModelAndView mv = new ModelAndView();
        RestTemplate restTemplate = new RestTemplate();

        String respostaServidor = restTemplate.getForObject(REST_SERVICE_URI + "/teste_rest/", String.class);
        Type listType = new TypeToken<List<Pessoa>>() {}.getType();
        List<Pessoa> listaPessoas = new Gson().fromJson(respostaServidor, listType);

        // Salva o log do sistema em arquivo, que é configurado em /resources/log4j.properties
        logger.error("Teste de log!!!!");

        mv.setViewName("lista_pessoas");
        mv.addObject("listaPessoas", listaPessoas);
        return mv;
    }

    /**
     * Recebe uma requisição Ajax e a responde conforme o resultado da mesma.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("cadastrar_pessoa")
    public void cadastrarPessoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        boolean isValid = true;
        String msgErro = null;
        String nome = null;
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.getParameter("nome_nova_pessoa"));

        try {
            pessoaDAOMySQL.insert(pessoa);
        } catch (PessoaDAOException e) {
            isValid = false;
            msgErro = e.getMessage();
        }

        map.put("isValid", isValid);
        map.put("msgErro", msgErro);
        map.put("pessoa", pessoa);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));
    }

}
