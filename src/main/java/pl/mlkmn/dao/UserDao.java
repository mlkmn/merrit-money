package pl.mlkmn.dao;

import pl.mlkmn.model.User;

import java.util.List;

public interface UserDao {

    User findById(Integer id);
    
    User findByLogin(String login);

    List<User> findAll();

    void save(User user);

    void update(User user);

    void delete(Integer id);

}
