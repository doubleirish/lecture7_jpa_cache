package edu.uw.data.lecture7.app;

import edu.uw.data.lecture7.dao.ClassicRepositoryCustom;
import edu.uw.data.lecture7.model.Customer;
import edu.uw.data.lecture7.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{

  static final Logger log = LoggerFactory.getLogger(App.class);


  public static void main(String[] args) {
    log.info("Initializing Spring context...");

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/applicationContext.xml");

    log.info("Spring context initialized.");

    AccountService accountService = (AccountService) applicationContext.getBean("accountService");
    ClassicRepositoryCustom dao = (ClassicRepositoryCustom) applicationContext.getBean("classicRepository");

    log.info("accountService."+accountService);

    log.info("dao." + dao);


    Customer customer =accountService.findCustomerById(357);
    System.out.println("customer "+customer);

    Customer customer2 =accountService.findCustomerById(357);
    System.out.println("customer again "+customer);
    assert customer== customer2;


//
//    accountService.findAllCustomersInUsState("CA");
//
//    accountService.findAllCustomersInUsState("CA");
//    accountService.getStatistics();
//
//    accountService.findAllCustomersInUsState("WA");
//    accountService.getStatistics();
//
//    accountService.findAllCustomersInUsState("CA");


    accountService.getStatistics();

  }
}
