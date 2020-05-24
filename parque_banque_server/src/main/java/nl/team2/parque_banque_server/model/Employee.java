package nl.team2.parque_banque_server.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Employee extends User {

    private String password;

    @ManyToOne
    private Role role;

    public Employee() {
    }

    public Employee(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address) {
        super(surName, firstName, affix, phoneNumber, eMailAddress, address);
    }

    public Employee(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address, String password, Role role) {
        super(surName, firstName, affix, phoneNumber, eMailAddress, address);
        this.password = password;
        this.role = role;
    }

    public Employee( String password, Role role) {
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return getId().equals(employee.getId()) &&
                getPassword().equals(employee.getPassword()) &&
                getRole().equals(employee.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getPassword(), getRole());
    }
}