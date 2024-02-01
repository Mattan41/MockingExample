package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryTest {




    @Test
    @DisplayName("when calling findAll then return all employees")
    void whenCallingFindAllThenReturnAllEmployees() {
    EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
        employeeRepository.save(new Employee("1", 1000));
        employeeRepository.save(new Employee("2", 2000));
        employeeRepository.save(new Employee("3", 3000));

        assertThat(employeeRepository.findAll()).hasSize(3);
        }

    @Test
    @DisplayName("when calling save then save employee")
    void whenCallingSaveThenSaveEmployee() {
        EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
        Employee employee = new Employee("1", 1000);
        Employee savedEmployee =  employeeRepository.save(employee);

        assertThat(savedEmployee).isEqualTo(employee);
        assertThat(employeeRepository.findAll()).containsExactly(employee);
    }

    @Test
    @DisplayName("calling save with existing id then override employee")
    void callingSaveWithExistingIdThenOverrideEmployee() {
        EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();
        Employee employee = new Employee("1", 1000);
        Employee savedEmployee =  employeeRepository.save(employee);
        Employee employee2 = new Employee("1", 2000);
        Employee savedEmployee2 =  employeeRepository.save(employee2);

        assertThat(savedEmployee2).isEqualTo(employee2);
        assertThat(employeeRepository.findAll()).containsExactly(employee2);
    }
}