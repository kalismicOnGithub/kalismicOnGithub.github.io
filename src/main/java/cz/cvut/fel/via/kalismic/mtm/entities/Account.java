package cz.cvut.fel.via.kalismic.mtm.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@NamedQueries({
    @NamedQuery(name = Account.FIND_ALL, query = "SELECT a FROM Account a")
})
public class Account implements Serializable {

    public static final String FIND_ALL = "Account.findAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TaskStack shortTerm;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TaskStack longTerm;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TaskStack regular;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TaskStack getShortTerm() {
        return shortTerm;
    }

    public void setShortTerm(TaskStack shortTerm) {
        this.shortTerm = shortTerm;
    }

    public TaskStack getLongTerm() {
        return longTerm;
    }

    public void setLongTerm(TaskStack longTerm) {
        this.longTerm = longTerm;
    }

    public TaskStack getRegular() {
        return regular;
    }

    public void setRegular(TaskStack regular) {
        this.regular = regular;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.via.kalismic.mtm.entities.Account[ id=" + id + " ]";
    }
}
