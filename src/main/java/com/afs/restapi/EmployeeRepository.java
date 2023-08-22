package com.afs.restapi;

import com.afs.restapi.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1L, "John Smith", 32, "Male", 5000));
        employees.add(new Employee(2L, "Jane Johnson", 28, "Female", 6000));
        employees.add(new Employee(3L, "David Williams", 35,"Male", 5500));
        employees.add(new Employee(4L, "Emily Brown", 23, "Female", 4500));
        employees.add(new Employee(5L, "Michael Jones", 40, "Male", 7000));
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

    public List<Employee> findByPage(Integer pageNumber, Integer pageSize) {
        return employees.stream()
                .skip((long) (pageNumber - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee insert(Employee newEmployee) {
        newEmployee.setId(generateNewId());
        employees.add(newEmployee);
        return newEmployee;
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

    private Long generateNewId() {
        return employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0L) + 1;
    }
}

