package cz.cvut.fel.via.kalismic.mtm.services;

import cz.cvut.fel.via.kalismic.mtm.entities.Account;
import cz.cvut.fel.via.kalismic.mtm.entities.TaskStack;
import cz.cvut.fel.via.kalismic.mtm.exeptions.NotFoundException;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AccountService {

    @PersistenceContext
    private EntityManager em;

    public TaskStack getShortTermStack(Long accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getShortTerm();
    }

    public TaskStack getLongTermStack(Long accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getLongTerm();
    }

    public TaskStack getRegularStack(Long accountId) throws Exception {
        Account account = getAccountById(accountId);
        return account.getRegular();
    }

    public Account getAccountById(Long id) throws Exception {
        Account result = em.find(Account.class, id);
        if (result != null) {
            return result;
        }
        throw new NotFoundException(Account.class.getName() + " with id " + id + " was not found!");
    }

    public Account getAccountByUsername(String username) throws Exception {
        Optional<Account> result = getAllAccounts().stream().filter(a -> a.getUsername().equals(username)).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        throw new NotFoundException(Account.class.getName() + " with username " + username + " was not found!");
    }

    private List<Account> getAllAccounts() {
        return em.createNamedQuery(Account.FIND_ALL, Account.class).getResultList();
    }

    public Account saveAccount(Account account) {
        if (account.getId() == 0) {
            em.persist(account);
            em.flush();
            em.refresh(account);
        } else {
            em.merge(account);
        }
        return account;
    }
}
