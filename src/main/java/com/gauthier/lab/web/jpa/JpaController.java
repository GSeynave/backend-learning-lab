package com.gauthier.lab.web.jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gauthier.lab.web.jpa.concurrent.ConcurrentUpdateService;
import com.gauthier.lab.web.transactional.TransactionLearningService;

@RestController
@RequestMapping("/jpa")
public class JpaController {

  JpaLearningService jpaLearningService;
  NPlusOneService nPlusOneService;
  TransactionLearningService transactionLearningService;
  ConcurrentUpdateService concurrentUpdateService;

  public JpaController(JpaLearningService jpaLearningService, NPlusOneService nPlusOneService,
      TransactionLearningService transactionLearningService, ConcurrentUpdateService concurrentUpdateService) {
    this.jpaLearningService = jpaLearningService;
    this.nPlusOneService = nPlusOneService;
    this.transactionLearningService = transactionLearningService;
    this.concurrentUpdateService = concurrentUpdateService;
  }

  @GetMapping("/experiment-1")
  public void experiment1() {
    System.out.println("---- EXPERIMENT 1 ----");
    jpaLearningService.experiment1(1L);
  }

  @GetMapping("/experiment-2")
  public void experiment2() {
    System.out.println("---- EXPERIMENT 2 ----");
    jpaLearningService.experiment2(1L);
  }

  @GetMapping("/experiment-3")
  public void experiment3() {
    System.out.println("---- EXPERIMENT 3 ----");
    jpaLearningService.experiment3(1L);
  }

  @GetMapping("/experiment-4")
  public void experiment4() {
    System.out.println("---- EXPERIMENT 4 ----");
    jpaLearningService.experiment4(1L);
  }

  @GetMapping("/experiment-5")
  public void experiment5() {
    System.out.println("---- EXPERIMENT 5 ----");
    jpaLearningService.experiment5(1L);
  };

  @GetMapping("/experiment-6")
  public void experiment6() {
    System.out.println("---- EXPERIMENT 6 ----");
    jpaLearningService.experiment6(1L);
  };

  @GetMapping("/experiment-7")
  public void experiment7() {
    System.out.println("---- EXPERIMENT 7 ----");
    nPlusOneService.experimentNPlusOne();
  };

  @GetMapping("/experiment-8")
  public void experiment8() {
    System.out.println("---- EXPERIMENT 8 ----");
    nPlusOneService.experimentNoNPlusOneWitHJoinFetch();
  };

  @GetMapping("/transactional/experiment-1")
  public void transactionalExperiment1() {
    System.out.println("---- TRANSACTIONAL EXPERIMENT 1 ----");
    transactionLearningService.outer(1L);
  };

  @GetMapping("/transactional/experiment-2")
  public void transactionalExperiment2() {
    System.out.println("---- TRANSACTIONAL EXPERIMENT 2 ----");
    transactionLearningService.outer2(1L);
  };

  @GetMapping("/transactional/experiment-3")
  public void transactionalExperiment3() {
    System.out.println("---- TRANSACTIONAL EXPERIMENT 3 ----");
    transactionLearningService.requiredExperiment();
  };

  @GetMapping("/transactional/experiment-4")
  public void transactionalExperiment4() {
    System.out.println("---- TRANSACTIONAL EXPERIMENT 4 ----");
    transactionLearningService.requiredNewExperiment();
  };

  @GetMapping("/concurrent/experiment-1/{delay}")
  public void concurrent1(@PathVariable("delay") int delay) {
    System.out.println("---- Concurrent EXPERIMENT 1 ----");
    concurrentUpdateService.optimisticLocking(delay);
  };

  @GetMapping("/concurrent/experiment-2/{delay}")
  public void concurrent2(@PathVariable("delay") int delay) {
    System.out.println("---- Concurrent EXPERIMENT 2 ----");
    concurrentUpdateService.pessimisticIncrement(delay);
  };
}
