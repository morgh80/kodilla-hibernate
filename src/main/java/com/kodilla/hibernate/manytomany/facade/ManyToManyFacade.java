package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManyToManyFacade {

    private static Logger LOGGER = LoggerFactory.getLogger(ManyToManyFacade.class);

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    public List<Company> findByCompanyName(String name) throws SearchProcessingException {
        if (name == "") {
            LOGGER.error(SearchProcessingException.ERR_NO_ARGS);
        }
        if (companyDao.retrieveCompaniesContaining(name).isEmpty()) {
            LOGGER.error(SearchProcessingException.ERR_NO_RESULTS);
            throw new SearchProcessingException(SearchProcessingException.ERR_NO_RESULTS);
        }
        LOGGER.info("Company found: " + companyDao.retrieveCompaniesContaining(name));
        return companyDao.retrieveCompaniesContaining(name);
    }

    public List<Employee> findByEmployeeName(String name) throws SearchProcessingException {
        if (name == "") {
            LOGGER.error(SearchProcessingException.ERR_NO_ARGS);
        }
        if (employeeDao.retrieveEmployeeContaining(name).isEmpty()) {
            LOGGER.error(SearchProcessingException.ERR_NO_RESULTS);
            throw new SearchProcessingException(SearchProcessingException.ERR_NO_RESULTS);
        }
        LOGGER.info("Employee found: " + employeeDao.retrieveEmployeeContaining(name));
        return employeeDao.retrieveEmployeeContaining(name);
    }

}
