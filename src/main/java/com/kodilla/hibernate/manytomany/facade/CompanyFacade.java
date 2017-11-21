package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyFacade {

    private static Logger LOGGER = LoggerFactory.getLogger(CompanyFacade.class);

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    public List<Company> findByCompanyName(String name) throws SearchProcessingException {
        if (name == "") {
            LOGGER.info(SearchProcessingException.ERR_NO_ARGS);
            throw new SearchProcessingException(SearchProcessingException.ERR_NO_ARGS);
        }
        List<Company> retrievedCompaniesNames = companyDao.retrieveCompaniesContaining(name);
        if (retrievedCompaniesNames.isEmpty()) {
            LOGGER.info("There are no results");
        } else {
            LOGGER.info("Company found: " + retrievedCompaniesNames);
        }
        return retrievedCompaniesNames;
    }

    public List<Employee> findByEmployeeName(String name) throws SearchProcessingException {
        if (name == "") {
            LOGGER.info(SearchProcessingException.ERR_NO_ARGS);
            throw new SearchProcessingException(SearchProcessingException.ERR_NO_ARGS);
        }
        List<Employee> retrievedEmployeesNames = employeeDao.retrieveEmployeeContaining(name);
        if (retrievedEmployeesNames.isEmpty()) {
            LOGGER.info("There are no results");
        } else {
            LOGGER.info("Employee found: " + retrievedEmployeesNames);
        }
        return retrievedEmployeesNames;
    }

}
