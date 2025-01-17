package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)

class EmployeesTest {

    @Mock
    EmployeeRepository employeeRepository;


    @Test
    @DisplayName("payEmployees should pay all employees")
    void payEmployeesShouldPayAllEmployees() {

        DummyBankService dummyBankService = new DummyBankService() {

            @Override
            public void pay(String id, double amount) {

            }
        };

        Mockito.when(employeeRepository.findAll()).thenReturn(List.of(new Employee("1", 1000), new Employee("2", 2000), new Employee("3", 3000)));

        Employees employees = new Employees(employeeRepository, dummyBankService);

        int payments = employees.payEmployees();

        assertThat(payments).isEqualTo(3);

    }

    @Test
    @DisplayName("payEmployees should not pay employee in case of runtime exception")
    void payEmployeesShouldNotPayEmployeeInCaseOfRuntimeException() {

            DummyBankService dummyBankService = new DummyBankService() {

                @Override
                public void pay(String id, double amount) {
                    if (id.equals("2"))
                        throw new RuntimeException();
                }
            };
            Mockito.when(employeeRepository.findAll()).thenReturn(List.of(new Employee("1", 1000), new Employee("2", 2000), new Employee("3", 3000)));
            Employees employees = new Employees(employeeRepository, dummyBankService);

            int payments = employees.payEmployees();

            assertThat(payments).isEqualTo(2);
            assertThat(employeeRepository.findAll().get(0).isPaid()).isTrue();
            assertThat(employeeRepository.findAll().get(1).isPaid()).isFalse();
            assertThat(employeeRepository.findAll().get(2).isPaid()).isTrue();

    }

}