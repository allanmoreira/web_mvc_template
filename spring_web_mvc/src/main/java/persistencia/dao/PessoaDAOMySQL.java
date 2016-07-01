package persistencia.dao;

import negocio.exception.PessoaDAOException;
import org.hibernate.Query;
import persistencia.entity.Pessoa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by allanmoreira on 17/05/16.
 */
public class PessoaDAOMySQL implements PessoaDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Pessoa> selectAll() throws PessoaDAOException {
        List queryList;
        try {
            Session session = this.sessionFactory.openSession();
            queryList = session.createQuery("from Pessoa").list();
            session.close();
        } catch (Exception e) {
            throw new PessoaDAOException("Houve um problema ao buscar a pessoa!");
        }
        return queryList;
    }

    public Pessoa selectById(int id) throws PessoaDAOException {
        Session session = null;
        Transaction transaction = null;
        Pessoa pessoa;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String queryString = "from Pessoa where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            pessoa = (Pessoa) query.uniqueResult();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new PessoaDAOException("Houve um problema ao buscar a pessoa!");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return pessoa;
    }

    public void insert(Pessoa pessoa) throws PessoaDAOException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(pessoa);
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new PessoaDAOException("Houve um problema ao adicionar a pessoa!");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Pessoa pessoa) throws PessoaDAOException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(pessoa);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new PessoaDAOException("Houve um problema ao excluir a pessoa!");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(Pessoa pessoa) throws PessoaDAOException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(pessoa);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            throw new PessoaDAOException("Houve um problema ao editar a pessoa!");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
