package persistencia.dao;

import negocio.exception.PessoaDAOException;
import persistencia.entity.Pessoa;

import java.util.List;

/**
 * Created by allanmoreira on 30/06/16.
 */
public interface PessoaDAO {

    List<Pessoa> selectAll() throws PessoaDAOException;
    Pessoa selectById(int id) throws PessoaDAOException;
    void insert(Pessoa pessoa) throws PessoaDAOException;
    void delete(Pessoa pessoa) throws PessoaDAOException;
}
