package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Role;
import springboot.api.bankapp.data.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) { this.roleRepository = roleRepository; }

    //Return all customer regardless of role
    public List<Role> getRoles() { return roleRepository.findAll(); }

    //Return a customer by their role id
    public Role getRole(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + roleId + " does not exist."));
    }

    //Create a new customer
    public Role createRole(Role role) { return roleRepository.save(role); }

    //Update a customer
    @Transactional
    public Role updateRole(Long roleId, String name) {
        Role updateRole = roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + roleId + " does not exist."));

        if(name != null) {
            updateRole.setName(name);
        }
        return roleRepository.save(updateRole);
    }

    //Delete a customer
    public boolean deleteRole(Long roleId) {
        roleRepository.findById(roleId).orElseThrow(() -> new IllegalStateException("Customer with ID: " + roleId + " does not exist."));

        try{
            roleRepository.deleteById(roleId);
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
            return false;
        }
        return true;
    }
}
