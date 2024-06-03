package it.epicode.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "addresses")
public class Address  {

  @Id
  @GeneratedValue
  private UUID id;

  private String street;

  @Column(name = "street_number")
  private String streetNumber;

  private String location;

  @Column(name = "postal_code")
  private int postalCode;

  @ManyToOne
  @JoinColumn(name = "county_id")
  @JsonIgnore
  private County county;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  @JsonIgnore
  private Customer customer;

  @Override
  public String toString() {
    return "Address{" +
            "id=" + id +
            ", street='" + street + '\'' +
            ", streetNumber='" + streetNumber + '\'' +
            ", location='" + location + '\'' +
            ", postalCode=" + postalCode +
            ", county=" + county +
            ", customer=" + customer +
            '}';
  }
}
