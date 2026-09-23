package com.gauthier.lab.web.jpa;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class JpaLearningService {
  private final JpaHeroRepository heroRepository;
  private final EntityManager entityManager;

  public JpaLearningService(JpaHeroRepository heroRepository, EntityManager entityManager) {
    this.heroRepository = heroRepository;
    this.entityManager = entityManager;
  }

  @Transactional
  public void experiment1(Long id) {
    JpaHero hero = heroRepository.findById(id).orElseThrow();
    System.out.println("---- HERO LOADED ----");

    hero.setLevel(hero.getLevel() + 1);

    System.out.println("---- HERO MODIFIED ----");

  }

  @Transactional
  public void experiment2(Long id) {
    JpaHero a = heroRepository.findById(id).orElseThrow();
    System.out.println("---- FIRST LOAD ----");

    JpaHero b = heroRepository.findById(id).orElseThrow();

    System.out.println("---- SECOND LOAD ----");

    System.out.println("same instance = " + (a == b));

  }

  @Transactional
  public void experiment3(Long id) {
    JpaHero hero = heroRepository.findById(id).orElseThrow();

    hero.setLevel(hero.getLevel() + 1);

    System.out.println("---- BEFORE FLUSH ----");

    heroRepository.flush();

    System.out.println("---- AFTER FLUSH ----");

    throw new RuntimeException("---- Rollback ----");
  }

  @Transactional
  public void experiment4(Long id) {
    JpaHero hero = new JpaHero("Merlin", 1);

    System.out.println("--- CREATED ---");
    System.out.println("id = " + hero.getId());
    System.out.println("managed = " + entityManager.contains(hero));

    entityManager.persist(hero);

    System.out.println("--- AFTER PERSIST ---");
    System.out.println("id = " + hero.getId());
    System.out.println("managed = " + entityManager.contains(hero));
  }

  @Transactional
  public void experiment5(Long id) {
    JpaHero hero = entityManager.find(JpaHero.class, id);

    System.out.println("--- LOADED ---");
    System.out.println("managed = " + entityManager.contains(hero));

    entityManager.detach(hero);

    System.out.println("--- DETACHED ---");
    System.out.println("managed = " + entityManager.contains(hero));

    hero.setLevel(99);

    System.out.println("--- MODIFIED DETACHED OBJECT ---");
  }

  @Transactional
  public void experiment6(Long id) {
    JpaHero detached = entityManager.find(JpaHero.class, id);

    entityManager.detach(detached);

    detached.setLevel(detached.getLevel() + 10);

    System.out.println("detached managed = " + entityManager.contains(detached));
    JpaHero managed = entityManager.merge(detached);

    System.out.println("original managed = " + entityManager.contains(detached));
    System.out.println("returned managed = " + entityManager.contains(managed));

    System.out.println("same instance = " + (detached == managed));
  }

  @Transactional
  public void experiment7(Long id) {
    JpaHero hero = entityManager.find(JpaHero.class, id);
    entityManager.remove(hero);
  }
}
