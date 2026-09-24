package com.gauthier.lab.web.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.gauthier.lab.web.jpaentityrelation.JpaGuild;

@Entity
@Table(name = "jpa_heroes")
public class JpaHero {

  public JpaHero() {
  }

  public JpaHero(Long id, String name, int level) {
    this.id = id;
    this.name = name;
    this.level = level;
  }

  public JpaHero(String name, int level) {
    this.name = name;
    this.level = level;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private int level;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "guild_id")
  private JpaGuild guild;

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

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public JpaGuild getGuild() {
    return guild;
  }

  public void setGuild(JpaGuild guild) {
    this.guild = guild;
  }
}
