package com.gauthier.lab.web.jpa;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaHeroRepository extends JpaRepository<JpaHero, Long> {

  @Query("""
        SELECT h
        FROM JpaHero h
        LEFT JOIN FETCH h.guild
      """)
  List<JpaHero> findAllWithGuild();

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("""
        SELECT h
        FROM JpaHero h
        WHERE h.id = :id
      """)
  Optional<JpaHero> findByIdForUpdate(@Param("id") Long id);
}
