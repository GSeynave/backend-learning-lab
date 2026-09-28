
package com.gauthier.lab.web.transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gauthier.lab.web.jpa.JpaHero;
import com.gauthier.lab.web.jpa.JpaHeroRepository;

@Service
public class HeroTransactionalService {

  private final JpaHeroRepository heroRepository;

  public HeroTransactionalService(JpaHeroRepository heroRepository) {
    this.heroRepository = heroRepository;
  }

  @Transactional
  public void inner(Long id) {
    JpaHero hero = heroRepository.findById(id).orElseThrow();

    hero.setLevel(hero.getLevel() + 1);

    throw new RuntimeException("---- Boom ----");
  }

  @Transactional
  public void requiredFailure(Long id) {
    JpaHero hero = heroRepository.findById(id).orElseThrow();

    hero.setLevel(hero.getLevel() + 10);

    throw new RuntimeException("---- inner failure ----");
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void requiresNew(Long id) {
    JpaHero hero = heroRepository.findById(id).orElseThrow();

    hero.setLevel(hero.getLevel() + 10);

    throw new RuntimeException("---- inner failure ----");
  }
}
