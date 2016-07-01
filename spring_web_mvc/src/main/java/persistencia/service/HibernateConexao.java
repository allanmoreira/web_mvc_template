package persistencia.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by Allan Moreira on 26/01/2016.
 */
public class HibernateConexao {

    private static HibernateConexao hibernateConexao;
    private Configuration cfg;
    private SessionFactory sessionFactory;

    private HibernateConexao() throws HibernateException {
        // Seta as configurações do hibernate, que está no spring-context.xml
        cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();

        /*
        // Para carregar o arquivo "persistencia.cfg.xml" de um diretório diferente do diretório padrão
        SessionFactory sessionFactory = new Configuration()
                .configure("/com/mkyong/persistence/persistencia.cfg.xml")
                .buildSessionFactory();
        */
    }

    public static synchronized HibernateConexao getInstance() throws HibernateException {
        if (hibernateConexao == null) {
            hibernateConexao = new HibernateConexao();
        }
        return hibernateConexao;
    }

    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }

    private void reconnect() throws HibernateException {
        this.sessionFactory = cfg.buildSessionFactory();
    }

}
