package com.restServicesSpring.restService.Employee.service;

import com.restServicesSpring.restService.Employee.model.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.restServicesSpring.restService.Employee.repository.EmployeeRepository;
import com.restServicesSpring.restService.Employee.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeServiceTest {

    @Test
    void create() {
    }
    @Autowired
    private MockMvc mvc;

    @Test
    void getAllEmployees() throws Exception {
        EmployeeEntity ee =  new EmployeeEntity( 1, "name1", "lastname1", "exemple@yahoo.com");
        System.out.println("employee name is: " + ee.toString());
        RequestBuilder request = MockMvcRequestBuilders.get("/employees");
        MvcResult result = mvc.perform(request).andReturn();
        assertTrue ( result.getResponse().getContentAsString().contains("[{\""));

    }

    @Test
    void getEmployeeById() {
    }

    @Test
    void createOrUpdateEmployee() throws Exception {

        EmployeeEntity ee =  new EmployeeEntity( 1, "name1", "lastname1", "exemple@yahoo.com");
        System.out.println("Employee is: " + ee.toString());

        EmployeeService employeeService = new EmployeeService();
        EmployeeEntity employee1  = employeeService.create(ee);
    }

    @Test
    void deleteEmployeeById() {
    }
}










