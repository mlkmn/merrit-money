package pl.mlkmn.dao;

import pl.mlkmn.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    Role findByType(String type);

    Role findById(int id);
    
}
