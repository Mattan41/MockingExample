package com.example;

import java.util.ArrayList;
import java.util.List;

class InMemoryEmployeeRepository implements EmployeeRepository{

    private List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

        @Override
        public Employee save(Employee e) {
            employees.removeIf(employee -> employee.getId().equals(e.getId()));
            employees.add(e);
            return e;
        }

}
