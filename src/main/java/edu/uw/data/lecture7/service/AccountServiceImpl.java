package edu.uw.data.lecture7.service;

import edu.uw.data.lecture7.dao.ClassicDao;
import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Employee;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Conor on 4/28/2015.
 */
@Transactional()
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource(name="classicDao")
    private ClassicDao classicDao;


    @Override
    public Customer findCustomerById(Integer id) {
       return classicDao.findCustomerById(id);
    }

    @Override
   public Employee findEmployeeById(Integer id) {
       return classicDao.findEmployeeById(id);
    }



    @Override
    public List<Customer> findAllCustomersInUsState(String usState) {
        return classicDao.findAllCustomersInUsState(usState);
    }

    @Override
    public List<Employee> findAllEmployeesWithFirstName_query_LAB(String usState) {
        return classicDao.findAllEmployeesWithFirstName_query_cache_LAB(usState);
    }

    @Override
    public Statistics getHibernateStatistics() {
          return classicDao.getHibernateStatistics();
    }



}
