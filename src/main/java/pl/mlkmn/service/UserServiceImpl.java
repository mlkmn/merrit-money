package pl.mlkmn.service;

import org.springframework.stereotype.Service;
import pl.mlkmn.model.User;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void saveOrUpdate(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
