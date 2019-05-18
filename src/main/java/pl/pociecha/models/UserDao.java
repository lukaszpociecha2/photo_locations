package pl.pociecha.models;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDao() {
    }

    public void addUser(User user) {
        /*EntityTransaction tx = entityManager.getTransaction();
        tx.begin();*/
        entityManager.persist(user);
        //tx.commit();

    }

    public User getUserById(Long id){
        return entityManager.find(User.class, id);
    }


}
