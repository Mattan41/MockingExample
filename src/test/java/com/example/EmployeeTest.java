package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    @DisplayName("getId returns id ")
    void getIdReturnsId() {
        Employee employee = new Employee("1", 1000);

        assertEquals("1", employee.getId());
    }

    @Test
    @DisplayName("setId sets new Id for Employee")
    void setIdSetsNewIdForEmployee() {
        Employee employee = new Employee("1", 1000);

        employee.setId("2");

        assertThat(employee.getId()).isEqualTo("2");


    }

    @Test
    @DisplayName("getSalary returns salary")
    void getSalaryReturnsSalary() {
        Employee employee = new Employee("1", 1000);

        assertThat(employee.getSalary()).isEqualTo(1000);
    }

    @Test
    @DisplayName("set salary for employee sets new salary for employee")
    void setSalaryForEmployeeSetsNewSalaryForEmployee() {
        Employee employee = new Employee("1", 1000);

        employee.setSalary(2000);

        assertThat(employee.getSalary()).isEqualTo(2000);

    }

    @Test
    @DisplayName("setPaid sets paid to true for employee")
    void setPaidSetsPaidToTrueForEmployee() {
        Employee employee = new Employee("1", 1000);

        employee.setPaid(true);

        assertThat(employee.isPaid()).isTrue();
    }
}