package springboot.api.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.api.bankapp.data.models.Role;
import springboot.api.bankapp.data.repository.RoleRepository;
import springboot.api.bankapp.exceptions.RoleNotFoundException;

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
    public Role getRole(Long roleId) throws RoleNotFoundException {
        return roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Customer with ID: " + roleId + " does not exist."));
    }

    //Create a new customer
    public Role createRole(Role role) { return roleRepository.save(role); }

    //Update a customer
    @Transactional
    public Role updateRole(Long roleId, String name) throws RoleNotFoundException{
        Role updatedRole = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Customer with ID: " + roleId + " does not exist."));

        if(name != null) {
            updatedRole.setName(name);
        }
        return roleRepository.save(updatedRole);
    }

    //Delete a customer
    public boolean deleteRole(Long roleId) throws RoleNotFoundException{
        roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Customer with ID: " + roleId + " does not exist."));
        roleRepository.deleteById(roleId);
        return true;
    }
}
