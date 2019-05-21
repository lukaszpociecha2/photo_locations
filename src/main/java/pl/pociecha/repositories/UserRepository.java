package pl.pociecha.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.pociecha.models.User;
import pl.pociecha.models.UserDetails;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRepository implements GenericRepository<User, Long>{

    @PersistenceContext
    EntityManager entityManager;

    public UserRepository() {
    }

    @Override
    public void save(User user) {
        if(user.getUserDetails()!=null){
            entityManager.persist(user.getUserDetails());
        }
        entityManager.persist(user);

    }

    @Override
    public User get(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User update(User user) {
        User userToUpdate = entityManager.find(User.class, user.getId());
        userToUpdate.setFirstName("Bożena");
        user.getUserDetails().setProfession("bussineswoman");
        entityManager.merge(user.getUserDetails());
        entityManager.merge(userToUpdate);
        return entityManager.find(User.class, userToUpdate.getId());
    }

    @Override
    public void delete(User user) {
        User userToBeRemoved = entityManager.find(User.class, user.getId());
        entityManager.remove(userToBeRemoved);
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
