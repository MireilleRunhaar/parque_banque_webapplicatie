package nl.team2.parque_banque_server.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Employee extends User {

    private int employeeNumber;
    private String password;

    @ManyToOne
    private Role role;

    public Employee() {
    }

    public Employee(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address) {
        super(surName, firstName, affix, phoneNumber, eMailAddress, address);
    }

    public Employee(String surName, String firstName, String affix, String phoneNumber, String eMailAddress, Address address, int employeeNumber, String password, Role role) {
        super(surName, firstName, affix, phoneNumber, eMailAddress, address);
        this.employeeNumber = employeeNumber;
        this.password = password;
        this.role = role;
    }

    public Employee(int employeeNumber, String password, Role role) {
        this.employeeNumber = employeeNumber;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
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
        return employeeNumber == employee.employeeNumber &&
                password.equals(employee.password) &&
                role.equals(employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeNumber, password, role);
    }
}