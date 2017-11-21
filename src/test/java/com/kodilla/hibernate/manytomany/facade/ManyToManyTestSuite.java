package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTestSuite {

    @Autowired
    private CompanyFacade facade;

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    @After
    public void cleanDatabase() {
        //cleanUp
        companyDao.deleteAll();
        employeeDao.deleteAll();
    }

    @Test
    public void shouldFindCompanyName() throws SearchProcessingException {
        //Given
        Company softwareMachine = new Company("Software Machine");
        List<Company> foundCompanies = new ArrayList<>();
        companyDao.save(softwareMachine);

        //When
        foundCompanies = facade.findByCompanyName("oftw");

        //Then
        Assert.assertEquals("Software Machine", foundCompanies.get(0).getName());
    }

    @Test
    public void shouldFindEmployeeName() throws SearchProcessingException {
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        List<Employee> foundEmployees = new ArrayList<>();
        employeeDao.save(johnSmith);

        //When
        foundEmployees = facade.findByEmployeeName("mi");

        //Then
        Assert.assertEquals("Smith", foundEmployees.get(0).getLastname());
    }

    @Test(expected = SearchProcessingException.class)
    public void shouldThrowNoArgumentsException() throws SearchProcessingException {
        //Given
        Company softwareMachine = new Company("Software Machine");
        companyDao.save(softwareMachine);

        //When
        facade.findByCompanyName("");
    }

    @Test
    public void shouldReturnEmptyList() throws SearchProcessingException {
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        List<Employee> foundEmployees = new ArrayList<>();
        employeeDao.save(johnSmith);
        //When
        foundEmployees = facade.findByEmployeeName(null);

        //Then
        Assert.assertEquals(0, foundEmployees.size());

    }
}

