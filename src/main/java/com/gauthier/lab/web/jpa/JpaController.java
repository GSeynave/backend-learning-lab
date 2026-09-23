package com.gauthier.lab.web.jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class JpaController {

  JpaLearningService jpaLearningService;

  public JpaController(JpaLearningService jpaLearningService) {
    this.jpaLearningService = jpaLearningService;
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

}
