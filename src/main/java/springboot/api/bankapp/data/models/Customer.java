package springboot.api.bankapp.data.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long customerId;
    Long pan;
    String uniqueId;
    String name;
    String email;
    String dob;

    @OneToOne(mappedBy = "customer")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<Account> account;

    public Customer(){};

    public Customer(Long customerId, Long pan, String uniqueId, String name, String email, String dob) {
        this.customerId = customerId;
        this.pan = pan;
        this.uniqueId = uniqueId;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Customer(Long pan, String uniqueId, String name, String email, String dob){
        this.pan = pan;
        this.uniqueId = uniqueId;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }

    public Long getPan() {
        return pan;
    }

    public void setPan(Long pan) { this.pan = pan; }

    public String getUniqueId() { return uniqueId; }

    public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getEmail(){ return email; }

    public void setEmail(String email){ this.email = email; }

    public String getDob(){ return dob; }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
