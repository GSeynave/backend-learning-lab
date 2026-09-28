package com.gauthier.lab.web.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaHeroRepository extends JpaRepository<JpaHero, Long> {

  @Query("""
        SELECT h
        FROM JpaHero h
        LEFT JOIN FETCH h.guild
      """)
  List<JpaHero> findAllWithGuild();
}
