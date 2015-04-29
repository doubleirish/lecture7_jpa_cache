package edu.uw.data.lecture7.dao;


import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Employee;
import edu.uw.data.lecture7.model.Office;
import edu.uw.data.lecture7.model.Order;
import org.hibernate.stat.Statistics;

import java.util.List;

/**
 * Created by credmond on 03/03/15.
 */



public interface ClassicRepositoryCustom{  // <Entity class , Primary Key aka Id class >)


    // Cached Entity
    Customer findCustomerById(Integer id);

    // not yet Cached Entity
    Employee findEmployeeById(Integer id)  ;


    // p6spy illustrator
    //named Query
    List<Customer> findCustomersByExample(Customer customer);



    List<Customer> findAllCustomersInUsState(String usState);

    List<Customer> findAllCustomersInUsState_SLOW(String usState) ;

    Customer findCustomerByCustomerName(String customerName);

    List<Order> findRecentOrdersForCustomer(Customer customer);


    // office employees
    List<Office> findAllOffices();

    List<Object[]> findSalesOfficeForEachCustomer();


    Statistics getStatistics();


}
