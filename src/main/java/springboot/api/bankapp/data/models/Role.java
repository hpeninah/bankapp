package springboot.api.bankapp.data.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long roleId;
    String name;

    @OneToOne(mappedBy = "role")
    private User user;

    public Role(){}

    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getRoleId() { return roleId; }

    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
