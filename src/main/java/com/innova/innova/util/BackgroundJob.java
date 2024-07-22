package com.innova.innova.util;


import com.innova.innova.service.PersonService;
import com.innova.innova.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BackgroundJob {

    private final TransactionService transactionService;
    private final PersonService personService;

    @Autowired
    public BackgroundJob(TransactionService transactionService, PersonService personService){
        this.transactionService = transactionService;
            this.personService = personService;
        }

   // @Scheduled(fixedRate = 5000)
    public void printHelloWorld() {
        System.out.println("Hello World");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void daily() {
        personService.getAllPersons()
                .stream()
                .forEach(person -> {
                    double amount = transactionService.getDailyTransaction(person.getId());
                    person.setDailyTransaction(amount);
                    personService.createPerson(person);
                });
    }

    @Scheduled(cron = "0 0 0 * * 1")  // Executes weekly at midnight on Monday
    public void weekly() {
        personService.getAllPersons()
                .stream()
                .forEach(person -> {
                    double amount = transactionService.getWeeklyTransaction(person.getId());
                    person.setWeeklyTransaction(amount);
                    personService.createPerson(person);
                });
    }

    @Scheduled(cron = "0 0 0 1 * ?")  // Executes monthly at midnight on the 1st of the month
    public void monthly() {
        personService.getAllPersons()
                .stream()
                .forEach(person -> {
                    double amount = transactionService.getMonthlyTransaction(person.getId());
                    person.setMonthlyTransaction(amount);
                    personService.createPerson(person);
                });
    }
}
