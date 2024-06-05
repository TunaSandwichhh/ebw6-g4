package it.epicode.energy.controllers;

import it.epicode.energy.entities.Address;
import it.epicode.energy.services.AddressService;
import it.epicode.energy.types.requests.create.CreateAddressRequestBody;
import it.epicode.energy.types.requests.update.UpdateAddressRequestBody;
import it.epicode.energy.types.responses.DeleteAddressResponseBody;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    public AddressService addressService;

    @GetMapping
    public ResponseEntity<Page<Address>> getAddresses(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(addressService.retrieveAllAdresses(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable UUID id) {
        return new ResponseEntity<>(addressService.retrieveAdressesById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody @Validated CreateAddressRequestBody addressRequestBody,
                                                 BindingResult validation) throws BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().stream().map(
                            objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc + curr));
        }
        return new ResponseEntity<>(addressService.addAddress(addressRequestBody), HttpStatus.CREATED);
    }

    @PatchMapping("{/id}")
    public ResponseEntity<Address> updateAddress(@RequestBody @Validated UpdateAddressRequestBody addressRequestBody,
                                                 @PathVariable UUID id,
                                                 BindingResult validation) throws BadRequestException {
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc+curr));
        }
        return new ResponseEntity<>(addressService.editAdress(id, addressRequestBody), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteAddressResponseBody> deleteAddress(@PathVariable UUID id){
        return new ResponseEntity<>(addressService.removeAddress(id), HttpStatus.OK);
    }
}
