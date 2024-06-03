package it.epicode.energy.entities;

import it.epicode.energy.entities.enums.CustomerType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "business_name")
  private String businessName;

  @Column(name = "vat_number")
  private int vatNumber;

  private String email;

  @Column(name = "creation_date")
  private LocalDate creationDate;

  @Column(name = "last_contact_date")
  private LocalDate lastContactDate;

  @Column(name = "yearly_revenue")
  private double yearlyRevenue;

  @Column(name = "certified_email")
  private String certifiedEmail;

  private String telephone;

  @Column(name = "contact_email")
  private String contactEmail;

  @Column(name = "contact_first_name")
  private String contactFirstName;

  @Column(name = "contact_last_name")
  private String contactLastName;

  @Column(name = "contact_telephone")
  private String contactTelephone;

  @Column(name = "company_logo")
  private String companyLogo;

  @Column(name = "customer_type")
  @Enumerated(EnumType.STRING)
  private CustomerType customerType;

  @OneToMany(mappedBy = "customer")
  private List<Address> addresses;

  @OneToMany(mappedBy = "customer")
  private List<Invoice> invoices;

  @Override
  public String toString() {
    return "Customer{" +
            "id=" + id +
            ", businessName='" + businessName + '\'' +
            ", vatNumber=" + vatNumber +
            ", email='" + email + '\'' +
            ", creationDate=" + creationDate +
            ", lastContactDate=" + lastContactDate +
            ", yearlyRevenue=" + yearlyRevenue +
            ", certifiedEmail='" + certifiedEmail + '\'' +
            ", telephone='" + telephone + '\'' +
            ", contactEmail='" + contactEmail + '\'' +
            ", contactFirstName='" + contactFirstName + '\'' +
            ", contactLastName='" + contactLastName + '\'' +
            ", contactTelephone='" + contactTelephone + '\'' +
            ", companyLogo='" + companyLogo + '\'' +
            ", customerType=" + customerType +
            '}';
  }
}
