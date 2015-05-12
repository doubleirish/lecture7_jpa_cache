package edu.uw.data.lecture7.dao;


import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Employee;
import edu.uw.data.lecture7.model.Office;
import edu.uw.data.lecture7.model.Order;
import edu.uw.data.lecture7.model.Product;
import org.hibernate.stat.Statistics;

import java.util.List;

/**
 * Created by credmond on 03/03/15.
 */



public interface ClassicDao {  // <Entity class , Primary Key aka Id class >)


    // Cached Entity
    Customer findCustomerById(Integer id);

    // not yet Cached Entity see LAB 1
    Employee findEmployeeById(Integer id)  ;




    // cached query
    List<Customer> findAllCustomersInUsState_query_cache(String usState);

    // cached query LAB
    List<Employee> findAllEmployeesWithFirstName_query_cache_LAB(String usState);


   // named-query cache example
    List<Product> findAllProducts_named_query_cache_example()  ;

    // named-query cache LAB
    List<Product> findByAvailability_named_query_cache_LAB()  ;

    // method caching example
    List<Order> findRecentOrdersForCustomer_method_caching(String customerName);


    // method caching lab (no params)
    List<Office> findAllOffices_method_caching_LAB();

    // method caching lab (with params)
    Customer findCustomerByCustomerName_method_caching_LAB(String customerName);

    List<Object[]> findSalesOfficeForEachCustomer_JCache_example();


    Statistics getHibernateStatistics();
    void  printEhcacheStatistics();


}
