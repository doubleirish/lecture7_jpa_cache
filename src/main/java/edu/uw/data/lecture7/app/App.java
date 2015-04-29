package edu.uw.data.lecture7.app;

import edu.uw.data.lecture7.dao.ClassicRepositoryCustom;
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

    log.info("dao."+dao);


    accountService.findAllCustomersInUsState("CA");
    accountService.printStatistics();

    accountService.findAllCustomersInUsState("WA");
    accountService.printStatistics();

    accountService.findAllCustomersInUsState("CA");
    accountService.printStatistics();

  }
}
