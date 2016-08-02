package pl.mlkmn.service;

import pl.mlkmn.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findByType(String type);

    Role findById(int id);
    
}
