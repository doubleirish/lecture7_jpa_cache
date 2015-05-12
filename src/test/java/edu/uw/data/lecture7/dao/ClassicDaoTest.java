package edu.uw.data.lecture7.dao;


import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Office;
import edu.uw.data.lecture7.model.Order;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Embedded database is  always initialized cleanly  as its stored in the target sub dir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/classic-spring.xml", "classpath:/cache-spring.xml",
        "classpath:/datasource-embedded-init.xml"
        //  "classpath:/datasource-embedded-init-p6spy.xml"
        //  "classpath:/datasource-standalone-test.xml"
        //  "classpath:/datasource-standalone-p6spy-test.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ClassicDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    static final Logger log = LoggerFactory.getLogger(ClassicDaoTest.class);

    @Resource(name = "classicDao")
    private ClassicDao classicDao;

    @Override
    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }


    @Test
    // TODO In the ClassicDaoImpl class annotate the method we call here with spring @Cacheable using the "mymethods" cache name
    public void findOffices_cache_method_Test_LAB() {
        long start;
        long duration;

        //
        // first call should pull from database and push into the method cache named "offices"
        //
        start = System.currentTimeMillis();
        classicDao.findAllOffices_method_caching_LAB(); // TODO add @Cacheable to the method impl  called here
        duration = System.currentTimeMillis() - start;
        log.info("1st  took " + (duration) + " ms");

        //
        // second call should pull from cache
        //
        start = System.currentTimeMillis();
        classicDao.findAllOffices_method_caching_LAB();
        duration = System.currentTimeMillis() - start;
        log.info("2nd  took " + duration + " ms");


        classicDao.getHibernateStatistics();

        classicDao.printEhcacheStatistics();

        //
        // assert we got a hit count in the "offices" cache we setup for findAllOffices_method_caching_LAB() method
        //
        CacheManager cacheManager = CacheManager.getInstance();
        Cache officesCache = cacheManager.getCache("mymethods");
        long cacheHits = officesCache.getStatistics().getCacheHits();
        System.out.println(" offices Cache hits =" + cacheHits);
        assertTrue(cacheHits > 0);

    }


    @Test
    // TODO In the ClassicDaoImpl class annotate the method we call here with spring @Cacheable using the "offices" cache name
    public void findCustomers_cacheable_method_with_params_Test_LAB() {
        long start;
        long duration;


        String customerCAF = "CAF Imports";
        String customerMini = "Mini Wheels Co.";

        //
        // first call should pull from database and push into the method cache named "offices"
        //
        start = System.currentTimeMillis();
        classicDao.findRecentOrdersForCustomer_method_caching(customerCAF);
        duration = System.currentTimeMillis() - start;
        log.info("1st orders for " + customerCAF + "  took " + (duration) + " ms");

        //
        // second call should pull from cache
        //
        start = System.currentTimeMillis();
        classicDao.findRecentOrdersForCustomer_method_caching(customerCAF);
        duration = System.currentTimeMillis() - start;
        log.info("2nd orders for " + customerCAF + "  took " + (duration) + " ms");


        //
        // third call should pull from DB
        //
        start = System.currentTimeMillis();
        classicDao.findRecentOrdersForCustomer_method_caching(customerMini);
        duration = System.currentTimeMillis() - start;
        log.info("3rd orders for " + customerMini + "  took " + (duration) + " ms");


        classicDao.getHibernateStatistics();

        classicDao.printEhcacheStatistics();

        //
        // assert we got a hit count in the "offices" cache we setup for findAllOffices_method_caching_LAB() method
        //
        CacheManager cacheManager = CacheManager.getInstance();
        Cache officesCache = cacheManager.getCache("customersByName");
        long cacheHits = officesCache.getStatistics().getCacheHits();
        System.out.println(" offices Cache hits =" + cacheHits);
        assertEquals("expected just one custorders cache hit ", 1, cacheHits);

    }


    @Test
    public void findAllCustomersInUsState_query_cache() {
        long start = System.currentTimeMillis();
        List<Customer> customers = classicDao.findAllCustomersInUsState_query_cache("CA");


        long duration = System.currentTimeMillis() - start;
        log.info("1st  took " + duration + " ms");
        start = System.currentTimeMillis();
        List<Customer> customers2 = classicDao.findAllCustomersInUsState_query_cache("CA");

        duration = System.currentTimeMillis() - start;
        log.info("2nd  took " + duration + " ms");

    }

    @Test
    public void findRecentOrdersForCustomer_method_caching_Example_Test() {
        String customerName = "CAF Imports";

        List<Order> orders = classicDao.findRecentOrdersForCustomer_method_caching(customerName);
        for (Order order : orders) {
            log.info("order    :" + order);
            log.info("  detail :" + order.getOrderDetail());
        }
    }

    @Test
    public void findAllOffices_method_caching_LAB() {
        List<Office> offices = classicDao.findAllOffices_method_caching_LAB();
        for (Office office : offices) {
            log.info("office " + office);
        }
    }



        @Test
        public void findCustomerByCustomerName_method_caching_LAB () {
            String customerCAF = "CAF Imports";

            Customer cust = classicDao.findCustomerByCustomerName_method_caching_LAB(customerCAF);
            cust = classicDao.findCustomerByCustomerName_method_caching_LAB(customerCAF);


            CacheManager cacheManager = CacheManager.getInstance();
            Cache customersByNameCache = cacheManager.getCache("customersByName");
            long cacheHits = customersByNameCache.getStatistics().getCacheHits();
            System.out.println(" customersByName Cache hits =" + cacheHits);
            assertEquals("expected just one customersByName   cache hit ", 1, cacheHits);

        }

        @Test
        public void findSalesOfficeForEachCustomer_JCache_example_Test () {
            List<Object[]> offCusts = classicDao.findSalesOfficeForEachCustomer_JCache_example();
            classicDao.findSalesOfficeForEachCustomer_JCache_example();
            for (Object[] offCust : offCusts) {
                System.out.println("the sales office for customer " + offCust[1] + " is " + offCust[0]);
            }

            CacheManager cacheManager = CacheManager.getInstance();
            Cache officesCache = cacheManager.getCache("custoff");
            long cacheHits = officesCache.getStatistics().getCacheHits();
            System.out.println(" offices Cache hits =" + cacheHits);
            assertEquals("expected just one custorders cache hit ", 1, cacheHits);
        }


    }