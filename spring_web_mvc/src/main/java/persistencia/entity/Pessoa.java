package persistencia.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by allan on 29/01/16.
 */
@Entity
public class Pessoa {
    private int idPessoa;
    private String nome;


    @Id
    @Column(name = "id_pessoa")
    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Basic
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa that = (Pessoa) o;

        if (idPessoa != that.idPessoa) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPessoa;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }
}
