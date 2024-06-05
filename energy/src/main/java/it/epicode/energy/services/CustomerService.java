package it.epicode.energy.services;

import it.epicode.energy.entities.Customer;
import it.epicode.energy.entities.enums.CustomerType;
import it.epicode.energy.repositories.CustomerRepository;
import it.epicode.energy.types.requests.create.CreateCustomerRequestBody;
import it.epicode.energy.types.requests.update.UpdateCustomerRequestBody;
import it.epicode.energy.types.responses.DeleteCustomerResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public Page<Customer> retrieveAllCustomers(int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return customerRepository.findAll(pageable);
  }

  public Customer retrieveCustomerById(UUID customerId) {
    return customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("Customer not found with id: " + customerId));
  }

  public Customer addCustomer(CreateCustomerRequestBody customerRequestBody) {
    Customer customerToCreate = new Customer();
    setCustomerFields(customerToCreate, customerRequestBody);

    return customerRepository.save(customerToCreate);
  }

  public Customer editCustomer(UUID customerId, UpdateCustomerRequestBody customerRequestBody) {
    Customer customerToUpdate = customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("Customer not found with id: " + customerId));
    updateCustomerFields(customerToUpdate, customerRequestBody);

    return customerRepository.save(customerToUpdate);
  }

  public DeleteCustomerResponseBody removeCustomer(UUID customerId) {
    Customer customerToDelete = customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("Customer not found with id: " + customerId));

    Customer customerToShow = new Customer();

    setCustomerFieldsForDeletion(customerToShow, customerToDelete);

    customerRepository.delete(customerToDelete);

    System.out.println(customerToShow);

    return new DeleteCustomerResponseBody("Customer deleted successfully", customerToShow);
  }

  /**
   * HELPER
   * @param customerToUpdate
   * @param customerRequestBody
   */
  public void updateCustomerFields(Customer customerToUpdate, UpdateCustomerRequestBody customerRequestBody) {
    if (customerRequestBody.getBusinessName() != null) {
      customerToUpdate.setBusinessName(customerRequestBody.getBusinessName());
    }
    if (customerRequestBody.getVatNumber() != 0) {
      customerToUpdate.setVatNumber(customerRequestBody.getVatNumber());
    }
    if (customerRequestBody.getEmail() != null) {
      customerToUpdate.setEmail(customerRequestBody.getEmail());
    }
    if (customerRequestBody.getLastContactDate() != null) {
      customerToUpdate.setLastContactDate(customerRequestBody.getLastContactDate());
    }
    if (customerRequestBody.getYearlyRevenue() != 0) {
      customerToUpdate.setYearlyRevenue(customerRequestBody.getYearlyRevenue());
    }
    if (customerRequestBody.getCertifiedEmail() != null) {
      customerToUpdate.setCertifiedEmail(customerRequestBody.getCertifiedEmail());
    }
    if (customerRequestBody.getTelephone() != null) {
      customerToUpdate.setTelephone(customerRequestBody.getTelephone());
    }
    if (customerRequestBody.getContactEmail() != null) {
      customerToUpdate.setContactEmail(customerRequestBody.getContactEmail());
    }
    if (customerRequestBody.getContactFirstName() != null) {
      customerToUpdate.setContactFirstName(customerRequestBody.getContactFirstName());
    }
    if (customerRequestBody.getContactLastName() != null) {
      customerToUpdate.setContactLastName(customerRequestBody.getContactLastName());
    }
    if (customerRequestBody.getContactTelephone() != null) {
      customerToUpdate.setContactTelephone(customerRequestBody.getContactTelephone());
    }
    if (customerRequestBody.getCompanyLogo() != null) {
      customerToUpdate.setCompanyLogo(customerRequestBody.getCompanyLogo());
    }
    if (customerRequestBody.getCustomerType() != null) {
      customerToUpdate.setCustomerType(CustomerType.valueOf(customerRequestBody.getCustomerType()));
    }
  }

  /**
   * HELPER
   * @param customerToCreate
   * @param customerRequestBody
   */
  public void setCustomerFields(Customer customerToCreate, CreateCustomerRequestBody customerRequestBody) {
    customerToCreate.setCustomerType(CustomerType.valueOf(customerRequestBody.getCustomerType().toUpperCase())); //<-- se utilizzo una dropdwon da frontend, non ho bisogno dell'upperCase()
    customerToCreate.setBusinessName(customerRequestBody.getBusinessName());
    customerToCreate.setCertifiedEmail(customerRequestBody.getCertifiedEmail());
    customerToCreate.setCompanyLogo(customerRequestBody.getCompanyLogo());
    customerToCreate.setContactEmail(customerRequestBody.getContactEmail());
    customerToCreate.setContactFirstName(customerRequestBody.getContactFirstName());
    customerToCreate.setContactLastName(customerRequestBody.getContactLastName());
    customerToCreate.setContactTelephone(customerRequestBody.getContactTelephone());
    customerToCreate.setEmail(customerRequestBody.getEmail());
    customerToCreate.setYearlyRevenue(customerRequestBody.getYearlyRevenue());
    customerToCreate.setVatNumber(customerRequestBody.getVatNumber());
    customerToCreate.setTelephone(customerRequestBody.getTelephone());
    customerToCreate.setCreationDate(LocalDate.now());
    if(customerRequestBody.getLastContactDate() != null) {
      customerToCreate.setLastContactDate(customerRequestBody.getLastContactDate());
    }
  }

  public void setCustomerFieldsForDeletion(Customer customerToCreate, Customer customerRequestBody) {
    customerToCreate.setCustomerType(customerRequestBody.getCustomerType());
    customerToCreate.setBusinessName(customerRequestBody.getBusinessName());
    customerToCreate.setCertifiedEmail(customerRequestBody.getCertifiedEmail());
    customerToCreate.setCompanyLogo(customerRequestBody.getCompanyLogo());
    customerToCreate.setContactEmail(customerRequestBody.getContactEmail());
    customerToCreate.setContactFirstName(customerRequestBody.getContactFirstName());
    customerToCreate.setContactLastName(customerRequestBody.getContactLastName());
    customerToCreate.setContactTelephone(customerRequestBody.getContactTelephone());
    customerToCreate.setEmail(customerRequestBody.getEmail());
    customerToCreate.setYearlyRevenue(customerRequestBody.getYearlyRevenue());
    customerToCreate.setVatNumber(customerRequestBody.getVatNumber());
    customerToCreate.setTelephone(customerRequestBody.getTelephone());
    customerToCreate.setCreationDate(LocalDate.now());
    customerToCreate.setLastContactDate(customerRequestBody.getLastContactDate());
    customerToCreate.setId(customerRequestBody.getId());
    customerToCreate.setLastContactDate(customerRequestBody.getLastContactDate());
    customerToCreate.setAddresses(customerRequestBody.getAddresses());
    customerToCreate.setInvoices(customerRequestBody.getInvoices());
  }

}
