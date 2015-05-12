package edu.uw.data.lecture7.service;

import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Employee;
import edu.uw.data.lecture7.model.Product;
import org.hibernate.stat.Statistics;

import java.util.List;

/**
 * Created by Conor on 4/28/2015.
 */
public interface AccountService {
    // Cached Entity
    Customer findCustomerById(Integer id);
    // not yet Cached Entity
    Employee findEmployeeById(Integer id);

    //query cache example
    List<Customer> findAllCustomersInUsState(String usState);

    //query cache lab
    List<Employee> findAllEmployeesWithFirstName_query_LAB(String usState);

    // namedQuery cache example
    List<Product> findAllProducts_named_query_cache_example();

    // namedQuery cache LAB
    List<Product> findByAvailability_named_query_cache_LAB();

    Statistics getHibernateStatistics();

}
