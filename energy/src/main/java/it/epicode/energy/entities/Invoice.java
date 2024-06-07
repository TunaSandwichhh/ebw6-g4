package it.epicode.energy.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.energy.entities.enums.InvoiceState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {

  @Id
  @GeneratedValue
  private int id;

  private LocalDate date;

  private double amount;

  @Column(name = "invoice_state")
  private InvoiceState invoiceState;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  @JsonIgnore
  private Customer customer;

  @Override
  public String toString() {
    return "Invoice{" +
            ", date=" + date +
            ", amount=" + amount +
            ", invoiceState=" + invoiceState +
            ", customer=" + customer +
            '}';
  }
}
