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
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {

    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User) session.load(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
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
    public void delete(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        User userToDelete = new User();
        userToDelete.setId(id);
        session.delete(userToDelete);
    }
}
