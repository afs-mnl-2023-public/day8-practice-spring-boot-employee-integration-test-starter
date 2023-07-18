package com.afs.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        this.employees.add(new Employee(1, "Lily1", 20, "Female", 8000));
        this.employees.add(new Employee(2, "Lily2", 20, "Female", 8000));
        this.employees.add(new Employee(3, "Lily3", 20, "Female", 8000));
        this.employees.add(new Employee(4, "Lily4", 20, "Female", 8000));
        this.employees.add(new Employee(5, "Lily5", 20, "Female", 8000));
        this.employees.add(new Employee(6, "Lily6", 20, "Female", 8000));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> findByPage(int pageNumber, int pageSize) {
        return employees.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee insert(Employee newEmployee) {
        newEmployee.setId(generateNewId());
        employees.add(newEmployee);
        return newEmployee;
    }

    private int generateNewId() {
        int maxId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0);
        return maxId + 1;
    }

    public Employee update(int id, Employee employee) {
        Employee employeeToUpdate = findById(id);
        employeeToUpdate.merge(employee);
        return employeeToUpdate;
    }

    public void delete(int id) {
        Employee toRemovedEmployee = findById(id);
        employees.remove(toRemovedEmployee);
    }
}

