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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTestSuite {

    @Autowired
    private ManyToManyFacade facade;

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
    public void shouldFindCompanyName() {
        //Given
        Company softwareMachine = new Company("Software Machine");
        List<Company> foundCompanies = new ArrayList<>();
        companyDao.save(softwareMachine);

        //When
        try {
            foundCompanies = facade.findByCompanyName("oftw");
        } catch (SearchProcessingException e) {
            //do nothing
        }
        //Then
        Assert.assertEquals("Software Machine", foundCompanies.get(0).getName());
    }

    @Test
    public void shouldFindEmployeeName() {
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        List<Employee> foundEmployees = new ArrayList<>();
        employeeDao.save(johnSmith);

        //When
        try {
            foundEmployees = facade.findByEmployeeName("mi");
        } catch (SearchProcessingException e) {
            //do nothing
        }
        //Then
        Assert.assertEquals("Smith", foundEmployees.get(0).getLastname());
    }

}
