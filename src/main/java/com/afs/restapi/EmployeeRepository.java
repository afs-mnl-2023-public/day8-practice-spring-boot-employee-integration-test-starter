package com.afs.restapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        this.employees.add(new Employee(1L, "Lily1", 20, "Female", 8000));
        this.employees.add(new Employee(2L, "Lily2", 20, "Female", 8000));
        this.employees.add(new Employee(3L, "Lily3", 20, "Female", 8000));
        this.employees.add(new Employee(4L, "Lily4", 20, "Female", 8000));
        this.employees.add(new Employee(5L, "Lily5", 20, "Female", 8000));
        this.employees.add(new Employee(6L, "Lily6", 20, "Female", 8000));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> findByPage(Integer page, Integer size) {
        return employees.stream()
                .skip((long) (page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    public Employee insert(Employee newEmployee) {
        newEmployee.setId(generateNewId());
        employees.add(newEmployee);
        return newEmployee;
    }

    private Long generateNewId() {
        return employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0L) + 1;
    }

    public Employee update(Long id, Employee employee) {
        Employee employeeToUpdate = findById(id);
        employeeToUpdate.merge(employee);
        return employeeToUpdate;
    }

    public void delete(Long id) {
        Employee toRemovedEmployee = findById(id);
        employees.remove(toRemovedEmployee);
    }
}

