package edu.uw.data.lecture7.service;

import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Employee;
import org.hibernate.stat.Statistics;

import java.util.List;

/**
 * Created by Conor on 4/28/2015.
 */
public interface AccountService {
    Customer findCustomerById(Integer id);
    Employee findEmployeeById(Integer id);
    List<Customer> findAllCustomersInUsState(String usState);

    //List<Office> findAllOffices(String usState);

    Statistics getStatistics();

}
