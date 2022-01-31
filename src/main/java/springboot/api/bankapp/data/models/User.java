package springboot.api.bankapp.data.models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    String username;
    String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customerId", nullable = false)
    private Customer customer;

    public User(){}

    public User(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUserId(){
        return userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
