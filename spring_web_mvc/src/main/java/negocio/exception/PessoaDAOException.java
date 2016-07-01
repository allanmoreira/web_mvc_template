package negocio.exception;

/**
 * Created by allanmoreira on 28/06/16.
 */
public class PessoaDAOException extends Exception {

    public PessoaDAOException() {
    }

    public PessoaDAOException(String message) {
        super(message);
    }

    public PessoaDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public PessoaDAOException(Throwable cause) {
        super(cause);
    }
}
