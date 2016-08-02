package pl.mlkmn.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.mlkmn.model.User;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {

    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findById(Integer id) {
        return super.getByKey(id);
    }

    @Override
    public User findByLogin(String login) {
        Criteria criteria = super.createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        return (User) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return (List<User>) sessionFactory
                .getCurrentSession()
                .createCriteria(User.class)
                .list();
    }

    @Override
    public void saveOrUpdate(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public void save(User user) {
        super.persist(user);
    }

    @Override
    public void deleteById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        User userToDelete = new User();
        userToDelete.setId(id);
        session.delete(userToDelete);
    }

    @Override
    public void delete(User user) {
        super.delete(user);
    }
}
