package edu.uw.data.lecture7.dao;

import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.model.Employee;
import edu.uw.data.lecture7.model.Office;
import edu.uw.data.lecture7.model.Order;
import net.sf.ehcache.CacheManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * simple single-table Jdbc example with try-resources and datasource
 */


@Repository("classicDao")
public class ClassicDaoImpl implements ClassicDao {

    static final Logger log = LoggerFactory.getLogger(ClassicDaoImpl.class);


    @PersistenceContext
    private EntityManager em;


    public Customer findCustomerById(Integer id){
      return em.find(Customer.class,id);
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return em.find(Employee.class, id);
    }

    public List<Customer> findCustomersByExample(Customer customerEx) {
        Session session = (Session) em.getDelegate();

        Example customerExample = Example.create(customerEx);
        Criteria criteria = session.createCriteria(Customer.class).add(customerExample);

        return (List<Customer>) criteria.list();
    }



    public List<Customer> findAllCustomersInUsState(String usState) {
        log.info("searching for customers in state " + usState);

        return em.createQuery(
                "SELECT c FROM Customer c WHERE c.state = :state", Customer.class)
                .setParameter("state", usState)
                .setHint("org.hibernate.cacheable", true)
                .getResultList();
    }


    // TODO QUERY LAB : add a hint to the query below to make it cacheable
    public List<Employee> findAllEmployeesWithFirstName_query_cache_LAB(String firstName) {
        log.info("searching for employees with firstName: " + firstName);

        return em.createQuery(
                "SELECT e FROM Employee e WHERE e.firstName = :firstName", Employee.class)
                .setParameter("firstName", firstName)
                  // TODO add hint here
                .getResultList();
    }






    @Cacheable("classic")
    public Customer findCustomerByCustomerName(String customerName) {
        return (Customer) em.createQuery(
                "SELECT c FROM Customer c WHERE c.customerName = :customerName")
                .setParameter("customerName", customerName)
                .getSingleResult();
    }

   @Cacheable("custorders")
    public List<Order> findRecentOrdersForCustomer(Customer cust) {
     long start = System.currentTimeMillis();
     List<Order> orders = em.createQuery(
         "SELECT o from Order o   " +
             " WHERE o.customer = :cust " +
             " order by orderDate ", Order.class)
         .setParameter("cust", cust)
         .setMaxResults(10)
         .getResultList();
     long duration = System.currentTimeMillis() -start;
     log.info("pulled orders for ["+cust.getCustomerNumber()+" ]from db in " +duration);
     return orders;
    }

    // TODO annotate this method with spring @Cacheable using the "offices" cache name
    public List<Office> findAllOffices() {
      long start = System.currentTimeMillis();
      List<Office> resultList = em.createQuery("FROM Office", Office.class)
              .getResultList();
      long duration = System.currentTimeMillis() -start;
      log.info("pulled offices from database in " +duration +" ms");
      return resultList;
    }

    public List<Object[]> findSalesOfficeForEachCustomer() {
        return em.createQuery("SELECT o.city, c.customerName FROM Office o, Customer c  WHERE c.salesRep.office = o  ORDER by c.customerName "
        ).getResultList();
    }



    public   org.hibernate.stat.Statistics getHibernateStatistics() {
        Session session  = (Session)em.getDelegate();
        SessionFactory sessionFactory = session.getSessionFactory();
        Statistics stats = sessionFactory.getStatistics();
        System.out.println("Hibernate Cache Stats ->" + stats);


        String regions[] = stats.getSecondLevelCacheRegionNames();

        for(String regionName:regions) {
            SecondLevelCacheStatistics stat2 = stats.getSecondLevelCacheStatistics(regionName);
            log.info("2nd Level Cache(" +regionName+") Put Count: "+stat2.getPutCount());
            log.info("2nd Level Cache(" +regionName+") HIt Count: "+stat2.getHitCount());
            log.info("2nd Level Cache(" +regionName+") Miss Count: "+stat2.getMissCount());
        }
        return stats;
    }

  public   void  printEhcacheStatistics() {
    CacheManager cacheManager = CacheManager.getInstance();
    String[] cacheNames = cacheManager.getCacheNames();
    for (String cacheName : cacheNames) {
      net.sf.ehcache.Statistics statistics = cacheManager.getCache(cacheName).getStatistics();
      log.info(cacheName + " - " + statistics.toString());
    }
  }



}
