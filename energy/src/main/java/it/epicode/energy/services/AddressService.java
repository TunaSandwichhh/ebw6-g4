package it.epicode.energy.services;

import it.epicode.energy.entities.Address;
import it.epicode.energy.entities.County;
import it.epicode.energy.entities.Customer;
import it.epicode.energy.repositories.AddressRepository;
import it.epicode.energy.repositories.CountyRepository;
import it.epicode.energy.repositories.CustomerRepository;
import it.epicode.energy.types.requests.create.CreateAddressRequestBody;
import it.epicode.energy.types.requests.update.UpdateAddressRequestBody;
import it.epicode.energy.types.responses.DeleteAddressResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository adressRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private CountyRepository countyRepo;

    public Page<Address> retrieveAllAdresses(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return adressRepo.findAll(pageable);
    }

    public Address retrieveAdressesById(UUID addressId) {
        return adressRepo.findById(addressId).orElseThrow(() -> new RuntimeException("Adress with id: " + addressId + "not found"));
    }


    public Address addAddress(CreateAddressRequestBody addressRequestBody) {

        Customer customerToFind = customerRepo.findById(addressRequestBody.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer with Id: " + addressRequestBody.getCustomerId() + "not found"));

        County countyToFind = countyRepo.findById(addressRequestBody.getCountyId())
                .orElseThrow(() -> new RuntimeException("County with Id: " + addressRequestBody.getCountyId() + "not found"));

        Address addressToCreate = new Address();

        setAddressFields(addressToCreate, addressRequestBody);
        addressToCreate.setCustomer(customerToFind);
        addressToCreate.setCounty(countyToFind);
        return adressRepo.save(addressToCreate);
    }

    public Address editAdress(UUID addressId, UpdateAddressRequestBody addressRequestBody) {

        Address addressToUpdate = adressRepo.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Adress with Id: " + addressId + "not found"));

        if (addressRequestBody.getCustomerId() != null) {
            Customer customerToFind = customerRepo.findById(addressRequestBody.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer with Id: " + addressRequestBody.getCustomerId() + "not found"));

            addressToUpdate.setCustomer(customerToFind);
        }


        if (addressRequestBody.getCountyId() > 0) {
            County countyToFind = countyRepo.findById(addressRequestBody.getCountyId())
                    .orElseThrow(() -> new RuntimeException("County with Id: " + addressRequestBody.getCountyId() + "not found"));
            addressToUpdate.setCounty(countyToFind);
        }

        updateAddressFields(addressToUpdate, addressRequestBody);
        return adressRepo.save(addressToUpdate);
    }

    public DeleteAddressResponseBody removeAddress(UUID addressId) {
        Address addressToDelete = adressRepo.findById(addressId).orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

        Address addressToShow = new Address();

        setAddressFieldsForDeletion(addressToShow, addressToDelete);
        adressRepo.delete(addressToDelete);
        System.out.println(addressToShow);
        return new DeleteAddressResponseBody("Customer deleted successfully", addressToShow);
    }


    /**
     * HELPER
     *
     * @param addressToCreate
     * @param addressRequestBody
     */

    public void setAddressFields(Address addressToCreate, CreateAddressRequestBody addressRequestBody) {
        addressToCreate.setStreet(addressRequestBody.getStreet());
        addressToCreate.setLocation(addressRequestBody.getLocation());
        addressToCreate.setPostalCode(addressRequestBody.getPostalCode());
        addressToCreate.setStreetNumber(addressRequestBody.getStreetNumber());
    }

    public void setAddressFieldsForDeletion(Address addressToCreate, Address addressRequestBody) {
        addressToCreate.setStreetNumber(addressRequestBody.getStreetNumber());
        addressToCreate.setStreet(addressRequestBody.getStreet());
        addressToCreate.setLocation(addressRequestBody.getLocation());
        addressToCreate.setPostalCode(addressRequestBody.getPostalCode());
    }

    /**
     * HELPER
     *
     * @param adressToUpdate
     * @param addressRequestBody
     */

    public void updateAddressFields(Address adressToUpdate, UpdateAddressRequestBody addressRequestBody) {

        if (addressRequestBody.getStreet() != null) {
            adressToUpdate.setStreet(addressRequestBody.getStreet());
        }

        if (addressRequestBody.getLocation() != null) {
            adressToUpdate.setStreet(addressRequestBody.getLocation());
        }

        if (addressRequestBody.getStreet() != null) {
            adressToUpdate.setStreet(addressRequestBody.getStreet());
        }
    }

}
