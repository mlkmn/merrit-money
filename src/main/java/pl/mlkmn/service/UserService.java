package pl.mlkmn.service;

import pl.mlkmn.model.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    User findByLogin(String login);

    List<User> findAll();

    void saveOrUpdate(User user);

    void delete(int id);

}
