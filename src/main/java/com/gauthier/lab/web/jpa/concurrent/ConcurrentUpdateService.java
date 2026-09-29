package com.gauthier.lab.web.jpa.concurrent;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gauthier.lab.web.jpa.JpaHero;
import com.gauthier.lab.web.jpa.JpaHeroRepository;

@Service
public class ConcurrentUpdateService {

  private final JpaHeroRepository heroRepository;

  public ConcurrentUpdateService(JpaHeroRepository heroRepository) {
    this.heroRepository = heroRepository;
  }

  @Transactional
  public void optimisticLocking(int delay) {
    JpaHero hero = heroRepository.findById(1L).orElseThrow();

    System.out.println(
        "LOADED id=" + hero.getId()
            + " level=" + hero.getLevel()
            + " version=" + hero.getVersion());

    try

    {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    hero.setLevel(hero.getLevel() + 1);

    System.out.println("UPDATING id=" + hero.getId() + " level=" + hero.getLevel() + " version=" + hero.getVersion());
  }

  @Transactional
  public void pessimisticIncrement(long delayMs) {
    JpaHero hero = heroRepository.findByIdForUpdate(1L).orElseThrow();

    System.out.println(
        "LOCKED id=" + hero.getId()
            + " level=" + hero.getLevel()
            + " version=" + hero.getVersion());

    try {
      Thread.sleep(delayMs);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }

    hero.setLevel(hero.getLevel() + 1);
  }
}
