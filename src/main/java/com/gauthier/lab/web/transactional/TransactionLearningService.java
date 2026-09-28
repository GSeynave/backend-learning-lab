package com.gauthier.lab.web.transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gauthier.lab.web.jpa.JpaHero;
import com.gauthier.lab.web.jpa.JpaHeroRepository;

@Service
public class TransactionLearningService {

  private final JpaHeroRepository heroRepository;
  private final HeroTransactionalService heroTransactionalService;

  public TransactionLearningService(JpaHeroRepository heroRepository,
      HeroTransactionalService heroTransactionalService) {
    this.heroRepository = heroRepository;
    this.heroTransactionalService = heroTransactionalService;
  }

  public void outer(Long id) {

    System.out.println("---- OUTER METHOD ----");
    inner(id);
  }

  public void outer2(Long id) {
    heroTransactionalService.inner(id);
  }

  @Transactional
  public void inner(Long id) {
    System.out.println("---- INNER METHOD ----");
    JpaHero hero = heroRepository.findById(id).orElseThrow();
    hero.setLevel(hero.getLevel() + 1);

    throw new RuntimeException("---- Boom ----");
  }

  @Transactional
  public void requiredExperiment() {
    JpaHero hero = heroRepository.findById(1L).orElseThrow();
    hero.setLevel(hero.getLevel() + 1);

    try {
      heroTransactionalService.requiredFailure(1L);
    } catch (RuntimeException e) {
      System.out.println("---- Inner Caught exception ----");
    }
    System.out.println("---- After inner call ----");
  }

  @Transactional
  public void requiredNewExperiment() {
    JpaHero hero = heroRepository.findById(1L).orElseThrow();
    hero.setLevel(hero.getLevel() + 1);

    try {
      heroTransactionalService.requiresNew(2L);
    } catch (RuntimeException e) {
      System.out.println("---- Inner Caught exception ----");
    }
    System.out.println("---- After inner call ----");
  }
}
