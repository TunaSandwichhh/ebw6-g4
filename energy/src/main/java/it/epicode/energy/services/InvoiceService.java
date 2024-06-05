package it.epicode.energy.services;

import it.epicode.energy.entities.Customer;
import it.epicode.energy.entities.Invoice;
import it.epicode.energy.entities.enums.InvoiceState;
import it.epicode.energy.repositories.CustomerRepository;
import it.epicode.energy.repositories.InvoiceRepository;
import it.epicode.energy.types.requests.create.CreateInvoiceRequestBody;
import it.epicode.energy.types.requests.update.UpdateInvoiceRequestBody;
import it.epicode.energy.types.responses.DeleteInvoiceResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class InvoiceService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Page<Invoice> retrieveAllCustomers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return invoiceRepository.findAll(pageable);
    }

    public Invoice retrieveInvoiceById(int invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(()-> new RuntimeException("Invoice not found with id: " + invoiceId));
    }

    public Invoice addInvoice(CreateInvoiceRequestBody invoiceRequestBody) {
        Customer customerToFind = customerRepository.findById(invoiceRequestBody.getCustomerId()).orElseThrow(()-> new RuntimeException("Customer not found"));
        Invoice invoiceToCreate = new Invoice();
        setInvoiceField(invoiceToCreate, invoiceRequestBody);

        invoiceToCreate.setCustomer(customerToFind);


        return invoiceRepository.save(invoiceToCreate);
    }

    public Invoice editInvoice(int invoiceId, UpdateInvoiceRequestBody invoiceRequestBody) {
        Invoice invoiceToUpdate = invoiceRepository.findById(invoiceId).orElseThrow(()-> new RuntimeException("Invoice not found with id: " + invoiceId));
        updateInvoiceFields(invoiceToUpdate, invoiceRequestBody);

        return invoiceRepository.save(invoiceToUpdate);
    }

    public DeleteInvoiceResponseBody removeInvoice(int invoiceId) {
        Invoice invoiceToDelete = invoiceRepository.findById(invoiceId).orElseThrow(()-> new RuntimeException("Invoice not found with id: " + invoiceId));

        Invoice invoiceToShow = new Invoice();

        setInvoiceFieldsForDeletion(invoiceToShow, invoiceToDelete);

        invoiceRepository.delete(invoiceToDelete);

        return new DeleteInvoiceResponseBody("Invoice deleted successfully", invoiceToShow);
    }





    public void setInvoiceField(Invoice invoiceToCreate, CreateInvoiceRequestBody invoiceRequestBody) {
        invoiceToCreate.setAmount(invoiceRequestBody.getAmount());
        invoiceToCreate.setInvoiceState(InvoiceState.valueOf(invoiceRequestBody.getInvoiceState()));
        invoiceToCreate.setDate(LocalDate.now());
    }

    public void updateInvoiceFields(Invoice invoiceToUpdate, UpdateInvoiceRequestBody invoiceRequestBody) {
        if (invoiceRequestBody.getInvoiceState() != null) {
            invoiceToUpdate.setInvoiceState(InvoiceState.valueOf(invoiceRequestBody.getInvoiceState()));
        }
        if (invoiceRequestBody.getAmount() != 0) {
            invoiceToUpdate.setAmount(invoiceRequestBody.getAmount());
        }
    }

    public void setInvoiceFieldsForDeletion(Invoice invoiceToCreate, Invoice invoiceRequestBody) {
        invoiceToCreate.setAmount(invoiceRequestBody.getAmount());
        invoiceToCreate.setCustomer(invoiceRequestBody.getCustomer());
        invoiceToCreate.setDate(invoiceRequestBody.getDate());
        invoiceToCreate.setInvoiceState(invoiceRequestBody.getInvoiceState());
        invoiceToCreate.setInvoiceNumber(invoiceRequestBody.getInvoiceNumber());
    }


}
