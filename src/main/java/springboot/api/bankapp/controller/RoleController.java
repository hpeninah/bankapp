package springboot.api.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.api.bankapp.data.models.Role;
import springboot.api.bankapp.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @GetMapping("/{roleId}")
    public Role getRoleById(@PathVariable("roleId") Long roleId) {
        try {
            return roleService.getRole(roleId);
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @PostMapping("")
    public Role createRole(@RequestBody Role role) { return roleService.createRole(role); }

    @PutMapping("/{roleId}/{roleName}")
    public Role updateRole(@PathVariable("roleId") Long roleId, @PathVariable("roleName") String roleName) {
        try {
            Role updatedRole = roleService.updateRole(roleId, roleName);
            return updatedRole;
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{roleId}")
    public boolean deleteRole(@PathVariable("roleId") Long roleId) {
        try{
            roleService.deleteRole(roleId);
            return true;
        } catch (IllegalStateException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
}
