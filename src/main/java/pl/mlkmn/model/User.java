package pl.mlkmn.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    
    private int id;
    
    private String login;
    
    private String password;
    
    private boolean activated;
    
    private Set<Role> roles = new HashSet<>();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
