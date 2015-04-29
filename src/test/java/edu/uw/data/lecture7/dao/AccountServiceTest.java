package edu.uw.data.lecture7.dao;


import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Employee;
import edu.uw.data.lecture7.service.AccountService;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

/**
 * Embedded database is  always initialized cleanly  as its stored in the target sub dir which is cleared out on each run
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/classic-spring.xml", "classpath:/cache-spring.xml",
      //  "classpath:/datasource-embedded-init.xml"
      "classpath:/datasource-embedded-init-p6spy.xml"
      //  "classpath:/datasource-standalone-test.xml"
        //   "classpath:/datasource-standalone-p6spy-test.xml"
})

public class AccountServiceTest extends AbstractJUnit4SpringContextTests {

    static final Logger log = LoggerFactory.getLogger(AccountServiceTest.class);

    @Resource(name="accountService")
    private AccountService service;




    @Test
    public void verifyCustomerEntityIsCached() {
        long start = System.currentTimeMillis();
        Customer customer = service.findCustomerById(1);
        log.info(" first cust " +customer);
        long duration  = System.currentTimeMillis() -start;
        log.info("1st  took " +duration+ " ms");

        start = System.currentTimeMillis();
        Customer customer2 = service.findCustomerById(1);
        log.info(" seond  cust " +customer);
        duration  = System.currentTimeMillis() -start;
        log.info("2nd  took " +duration+ " ms");

        Statistics stats = service.getStatistics();


        String regionName = "edu.uw.data.lecture7.model.Customer";
        SecondLevelCacheStatistics level2CustomerEntityStats = stats.getSecondLevelCacheStatistics(regionName);

            log.info("2nd Level Cache(" + regionName + ") Put Count: " + level2CustomerEntityStats.getPutCount());
            log.info("2nd Level Cache(" + regionName + ") HIt Count: " + level2CustomerEntityStats.getHitCount());
            log.info("2nd Level Cache(" + regionName + ") Miss Count: " + level2CustomerEntityStats.getMissCount());

        assertThat(level2CustomerEntityStats.getHitCount() ,is(greaterThan(0L)));

    }

    @Test
    public void verifyEmployeeEntityIsCached_LAB() {

        //
        // lookup entity first time , ( will be added to cache if cache is enabled )
        //
        long start = System.currentTimeMillis();
        int id = 1002;
        Employee employee = service.findEmployeeById(id);
        log.info(" first employee " +employee);
        long duration  = System.currentTimeMillis() -start;
        log.info("1st  took " +duration+ " ms");

        //
        // lookup entity second time , ( will be pulled from  cache if cache is enabled )
        //
        start = System.currentTimeMillis();
        Employee employee2 = service.findEmployeeById(id);
        log.info(" seond  employee " +employee);
        duration  = System.currentTimeMillis() -start;
        log.info("2nd  took " +duration+ " ms");

        Statistics stats = service.getStatistics();

        //
        // examine Level 2 stats  to see if
        //
        String regionName = "edu.uw.data.lecture7.model.Employee";
        SecondLevelCacheStatistics level2EmployeeEntityStats = stats.getSecondLevelCacheStatistics(regionName);
        assertNotNull("ehcache region may be missing/correctly-setup, or @Cache annotation may not exist for: "+regionName,level2EmployeeEntityStats);
        log.info("2nd Level Cache(" + regionName + ") Put Count: " + level2EmployeeEntityStats.getPutCount());
        log.info("2nd Level Cache(" + regionName + ") HIt Count: " + level2EmployeeEntityStats.getHitCount());
        log.info("2nd Level Cache(" + regionName + ") Miss Count: " + level2EmployeeEntityStats.getMissCount());

        assertThat(level2EmployeeEntityStats.getHitCount() ,is(greaterThan(0L)));

    }





    @Test
    public void verifyQueryIsCached() {

        //
        // call the    query  the first ime
        //
        long start = System.currentTimeMillis();
        List<Customer> customers = service.findAllCustomersInUsState("CA");
        long duration  = System.currentTimeMillis() -start;
        log.info("1st  took " +duration+ " ms");


        //
        // make same query a second time
        //
        start = System.currentTimeMillis();
        List<Customer> customers2 = service.findAllCustomersInUsState("CA");
        duration  = System.currentTimeMillis() -start;
        log.info("2nd  took " +duration+ " ms");

        //
        //  check the hibernate cache hit stats and verify we got a hit on StandardQueryCache
        //
        Statistics stats = service.getStatistics();


        String regionName = "org.hibernate.cache.internal.StandardQueryCache";
        SecondLevelCacheStatistics level2QueryCacheStats = stats.getSecondLevelCacheStatistics(regionName);

        log.info("2nd Level Cache(" + regionName + ") Put Count: " + level2QueryCacheStats.getPutCount());
        log.info("2nd Level Cache(" + regionName + ") HIt Count: " + level2QueryCacheStats.getHitCount());
        log.info("2nd Level Cache(" + regionName + ") Miss Count: " + level2QueryCacheStats.getMissCount());

        assertThat(level2QueryCacheStats.getHitCount(), is(greaterThan(0L)));  //QUEY CACHE HIT

    }


















}