package com.gauthier.lab.web.jpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class NPlusOneService {
  private final JpaHeroRepository heroRepository;
  private final EntityManager entityManager;

  public NPlusOneService(JpaHeroRepository heroRepository, EntityManager entityManager) {
    this.heroRepository = heroRepository;
    this.entityManager = entityManager;
  }

  @Transactional
  public void experimentNPlusOne() {
    List<JpaHero> heroes = heroRepository.findAll();
    System.out.println("---- HEROES LOADED ----");

    for (JpaHero hero : heroes) {
      if (hero.getGuild() == null) {
        System.out.println("hero = " + hero.getName() + " -> no guild");
      } else {
        System.out.println("hero = " + hero.getName() + " -> " + hero.getGuild().getName());
      }
    }

    System.out.println("---- DONE ----");

  }

  @Transactional
  public void experimentNoNPlusOneWitHJoinFetch() {
    List<JpaHero> heroes = heroRepository.findAllWithGuild();
    System.out.println("---- HEROES LOADED ----");

    for (JpaHero hero : heroes) {
      if (hero.getGuild() == null) {
        System.out.println("hero = " + hero.getName() + " -> no guild");
      } else {
        System.out.println("hero = " + hero.getName() + " -> " + hero.getGuild().getName());
      }
    }

    System.out.println("---- DONE ----");

  }
}
