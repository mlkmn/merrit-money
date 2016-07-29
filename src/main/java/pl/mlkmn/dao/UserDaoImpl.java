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
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {

    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User findById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (User) session.load(User.class, id);
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        return (User) criteria.uniqueResult();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return (List<User>) sessionFactory
                .getCurrentSession()
                .createCriteria(User.class)
                .list();
    }

    @Override
    @Transactional
    public void save(User user) {

    }

    @Override
    @Transactional
    public void update(User user) {

    }

    @Override
    public void delete(Integer id) {

    }
}
