package it.epicode.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "counties")
public class County {

  @Id
  @Column(name = "county_number")
  private int countyNumber;

  @Column(name = "county_name")
  private String countyName;

  @Column(name = "province_code")
  private int provinceCode;

  @ManyToOne
  @JoinColumn(name = "province_name")
  @JsonIgnore
  private Province province;

  @Override
  public String toString() {
    return "County{" +
            "countyNumber=" + countyNumber +
            ", countyName='" + countyName + '\'' +
            ", provinceCode=" + provinceCode +
            ", province=" + province +
            '}';
  }
}