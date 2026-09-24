package com.gauthier.lab.web.jpaentityrelation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relationship")
public class RelationShipController {

  private final JpaEntityRelationService jpaEntityRelationService;

  public RelationShipController(JpaEntityRelationService jpaEntityRelationService) {
    this.jpaEntityRelationService = jpaEntityRelationService;
  }

  @GetMapping("/experiment1")
  public String experimentRelationship() {
    jpaEntityRelationService.experimentRelationship(1L);
    return "Experiment completed";
  }

  @GetMapping("/experiment2")
  public String experimentRelationship2() {
    jpaEntityRelationService.experimentRelationshipInverseSide(1L);
    return "Experiment completed";
  }

  @GetMapping("/experiment3")
  public String experimentRelationship3() {
    jpaEntityRelationService.experimentRelationshipOwningSide(1L);
    return "Experiment completed";
  }
}
