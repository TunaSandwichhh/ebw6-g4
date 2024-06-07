package it.epicode.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "counties")
public class County {

  @Id
  @GeneratedValue
  private int id;

  @Column(name = "county_number")
  private int countyNumber;

  @Column(name = "county_name")
  private String countyName;

  @Column(name = "province_name")
  private String provinceName;

  @ManyToOne
  @JoinColumn(name = "province_code")
  @JsonIgnore
  private Province province;

  @Override
  public String toString() {
    return "County{" +
            "countyNumber=" + countyNumber +
            ", countyName='" + countyName + '\'' +
            ", provinceCode=" + provinceName +
            ", province=" + province +
            '}';
  }
}