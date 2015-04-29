package edu.uw.data.lecture7.dao;


import edu.uw.data.lecture7.model.Customer;

import java.util.List;

/**
 * Created by credmond on 03/03/15.
 */


public interface ClassicRepository
       // extends CrudRepository<Customer, Integer>, ClassicRepositoryCustom
{  // <Entity class , Primary Key aka Id class >)

    Customer  findOne(Integer id);

    List<Customer> findAll();




}
