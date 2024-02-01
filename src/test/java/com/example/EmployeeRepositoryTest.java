package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}