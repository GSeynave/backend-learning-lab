package com.gauthier.lab.web.jpaentityrelation;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gauthier.lab.web.jpa.JpaHero;

@Service
public class JpaEntityRelationService {
  private final EntityManager entityManager;

  public JpaEntityRelationService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  public void experimentRelationship(Long id) {
    JpaHero hero = entityManager.find(JpaHero.class, id);

    System.out.println("----- HERO LOADED -----");

    JpaGuild guild = hero.getGuild();

    System.out.println("----- GET GUILD CALLED -----");

    System.out.println("guild name = " + guild.getName());

    System.out.println("----- GUILD NAME READ -----");
  }

  @Transactional
  public void experimentRelationshipInverseSide(Long id) {
    JpaHero hero = entityManager.find(JpaHero.class, id);
    JpaGuild mages = entityManager.find(JpaGuild.class, 2L);

    mages.getHeroes().add(hero);

    entityManager.flush();

  }

  @Transactional
  public void experimentRelationshipOwningSide(Long id) {
    JpaHero hero = entityManager.find(JpaHero.class, id);
    JpaGuild mages = entityManager.find(JpaGuild.class, 2L);

    hero.setGuild(mages);

    entityManager.flush();
  }
}
