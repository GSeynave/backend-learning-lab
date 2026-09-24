package com.gauthier.lab.web.jpaentityrelation;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.gauthier.lab.web.jpa.JpaHero;

@Entity
@Table(name = "jpa_guilds")
public class JpaGuild {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToMany(mappedBy = "guild", fetch = FetchType.LAZY, orphanRemoval = true)
  private List<JpaHero> heroes = new ArrayList<>();

  protected JpaGuild() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<JpaHero> getHeroes() {
    return heroes;
  }

  public void setHeroes(List<JpaHero> heroes) {
    this.heroes = heroes;
  }
}
