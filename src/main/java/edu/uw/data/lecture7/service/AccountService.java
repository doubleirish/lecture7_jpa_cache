package edu.uw.data.lecture7.service;

import edu.uw.data.lecture7.model.Customer;

import java.util.List;

/**
 * Created by Conor on 4/28/2015.
 */
public interface AccountService {
    Customer findCustomerById(Integer id);
    Customer findCustomerByName(String name);
    List<Customer> findAllCustomersInUsState(String usState);

    void printStatistics( );

}
