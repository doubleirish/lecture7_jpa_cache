package edu.uw.data.lecture7.service;

import edu.uw.data.lecture7.dao.ClassicRepositoryCustom;
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
    @Resource(name="classicRepository")
    private ClassicRepositoryCustom classicRepository;


    @Override
    public Customer findCustomerById(Integer id) {
       return classicRepository.findCustomerById(id);
    }

    @Override
   public Employee findEmployeeById(Integer id) {
       return classicRepository.findEmployeeById(id);
    }



    @Override
    public List<Customer> findAllCustomersInUsState(String usState) {
        return classicRepository.findAllCustomersInUsState(usState);
    }

    @Override
    public Statistics getStatistics() {
          return classicRepository.getHibernateStatistics();
    }



}
