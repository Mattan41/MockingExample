package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    @DisplayName("getId returns id ")
    void getIdReturnsId() {
        Employee employee = new Employee("1", 1000);

        assertEquals("1", employee.getId());
    }

}