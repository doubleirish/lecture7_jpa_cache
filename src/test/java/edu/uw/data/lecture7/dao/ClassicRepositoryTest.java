package edu.uw.data.lecture7.dao;


import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Office;
import edu.uw.data.lecture7.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Embedded database is  always initialized cleanly  as its stored in the target sub dir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/queryapp-spring.xml", "classpath:/cache-spring.xml",
      //  "classpath:/datasource-embedded-init.xml"
      //  "classpath:/datasource-embedded-init-p6spy.xml"
      //  "classpath:/datasource-standalone-test.xml"
           "classpath:/datasource-standalone-p6spy-test.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ClassicRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    static final Logger log = LoggerFactory.getLogger(ClassicRepositoryTest.class);

    @Resource(name="classicRepository")
    private ClassicRepositoryCustom classicRepository;

    @Override
    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }


    @Test
    public void p6spy() {
        Customer customerEx =new Customer();
        customerEx.setCountry("USA");
        customerEx.setState("WA");
        customerEx.setCustomerName("custname");
        customerEx.setContactFirstname("first");
        customerEx.setContactLastname("last");
        customerEx.setAddressLine1("124 main st");
        customerEx.setCity("seattle");
        customerEx.setAddressLine2("WA");
        List<Customer> customers =classicRepository.findCustomersByExample(customerEx);
        log.info("using customer search criteria :"+customerEx);
        for (Customer customer : customers) {
           log.info("found by example :"+customer);
        }
    }




    @Test
    public void findAllCustomersInUsState_SLOW() {
        long start = System.currentTimeMillis();
        List<Customer> customers = classicRepository.findAllCustomersInUsState_SLOW("CA");


        long duration  = System.currentTimeMillis() -start;
        log.info("1st  took " +duration+ " ms");
        start = System.currentTimeMillis();
        List<Customer> customers2 = classicRepository.findAllCustomersInUsState_SLOW("CA");

          duration  = System.currentTimeMillis() -start;
        log.info("2nd  took " +duration+ " ms");


        start = System.currentTimeMillis();
         classicRepository.findAllCustomersInUsState_SLOW("CA");
        duration  = System.currentTimeMillis() -start;
        log.info("3rd  took " +duration+ " ms");

        classicRepository.printStatistics( ) ;

    }






    @Test
    public void findAllCustomersInUsState() {
        long start = System.currentTimeMillis();
        List<Customer> customers = classicRepository.findAllCustomersInUsState("CA");


        long duration  = System.currentTimeMillis() -start;
        log.info("1st  took " +duration+ " ms");
         start = System.currentTimeMillis();
        List<Customer> customers2 = classicRepository.findAllCustomersInUsState("CA");

        duration  = System.currentTimeMillis() -start;
        log.info("2nd  took " +duration+ " ms");

    }

    @Test
    public void findOrdersForCustomer() {
        String customerName = "CAF Imports";
        Customer customer = classicRepository.findCustomerByCustomerName(customerName);
        log.info("found customer " + customer);
        assertThat(customer.getCustomerName(), is(customerName));

        List<Order> orders = classicRepository.findRecentOrdersForCustomer(customer);
        for (Order order : orders) {
            log.info("order    :" + order);
            log.info("  detail :" + order.getOrderDetail());
        }
    }

    @Test
    public void findAllOffices() {
        List<Office> offices = classicRepository.findAllOffices();
        for (Office office : offices) {
            log.info("office " + office);
        }
    }

    @Test
    public void findSalesOfficeForEachCustomer() {
        List<Object[]> offCusts = classicRepository.findSalesOfficeForEachCustomer();
        for (Object[] offCust : offCusts) {
            System.out.println("the sales office for customer " + offCust[1] + " is " + offCust[0]);
        }
    }






}