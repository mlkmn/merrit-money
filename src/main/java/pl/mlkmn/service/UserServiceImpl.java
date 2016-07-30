package pl.mlkmn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mlkmn.dao.UserDao;
import pl.mlkmn.model.User;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    UserDao userDao;
    
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
