package nl.team2.parque_banque_server.model;

import java.util.Objects;

public class Employee extends User {

    private Long id;
    private String password;
    private Role role;

    public Employee() {
        super();
    }

    public Employee(Long id, String password, Role role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public Employee(String name, String address, String zipcode, String phoneNumber, String eMailAdress, Long id, String password, Role role) {
        super(name, address, zipcode, phoneNumber, eMailAdress);
        this.id = id;
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
