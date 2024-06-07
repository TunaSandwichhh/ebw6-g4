package it.epicode.energy.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "provinces")
public class Province {

  @Id
  @GeneratedValue
  private int id;

  @Column(name = "province_name")
  private String provinceName;

  private String initials;

  private String region;

  @OneToMany(mappedBy = "province")
  private List<County> counties;

  @Override
  public String toString() {
    return "Province{" +
            "provinceName='" + provinceName + '\'' +
            ", initials='" + initials + '\'' +
            ", region='" + region + '\'' +
            '}';
  }
}
