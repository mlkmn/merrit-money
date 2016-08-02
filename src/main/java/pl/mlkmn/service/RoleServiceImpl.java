package pl.mlkmn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mlkmn.dao.RoleDao;
import pl.mlkmn.model.Role;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    
    RoleDao roleDao;
    
    @Autowired
    public void setUserDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findByType(String type) {
        return roleDao.findByType(type);
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }
}
