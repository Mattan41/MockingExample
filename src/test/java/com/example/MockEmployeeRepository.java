package com.example;

import java.util.List;

class  MockEmployeeRepository implements EmployeeRepository{

    List<Employee> employees = List.of(
            new Employee("1", 1000),
            new Employee("2", 2000),
            new Employee("3", 3000));

    @Override
    public List<Employee> findAll() {
        return employees;
    }

        @Override
        public Employee save(Employee e) {
            employees.removeIf(employee -> employee.getId().equals(e.getId()));
            employees.add(e);
            return e;
        }

}
