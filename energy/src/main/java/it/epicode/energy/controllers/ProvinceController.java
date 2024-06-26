package it.epicode.energy.controllers;

import com.opencsv.exceptions.CsvValidationException;
import it.epicode.energy.entities.Province;
import it.epicode.energy.services.ProvinceService;
import it.epicode.energy.types.requests.create.CreateProvinceRequestBody;
import it.epicode.energy.types.requests.update.UpdateProvinceRequestBody;
import it.epicode.energy.types.responses.DeleteProvinceResponseBody;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

  @Autowired
  private ProvinceService provinceService;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<Page<Province>> getProvinces(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy) {
    return new ResponseEntity<>(provinceService.retrieveAllProvinces(page, size, sortBy), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<Province> getProvince(@PathVariable int id) {
    return new ResponseEntity<>(provinceService.retrieveProvinceById(id), HttpStatus.OK);
  }

  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<Province> createProvince(@RequestBody @Validated CreateProvinceRequestBody provinceRequestBody, BindingResult validation) throws BadRequestException {
    if(validation.hasErrors()){
      throw new BadRequestException(validation.getAllErrors()
              .stream()
              .map(objectError -> objectError.getDefaultMessage())
              .reduce("", (acc, curr) -> acc+curr));
    }
    return new ResponseEntity<>(provinceService.addProvince(provinceRequestBody), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<Province> updateProvince(@RequestBody @Validated UpdateProvinceRequestBody provinceRequestBody,
                                                 @PathVariable int id,
                                                 BindingResult validation) throws BadRequestException {
    if(validation.hasErrors()){
      throw new BadRequestException(validation.getAllErrors()
              .stream()
              .map(objectError -> objectError.getDefaultMessage())
              .reduce("", (acc, curr) -> acc+curr));
    }
    return new ResponseEntity<>(provinceService.editProvince(id, provinceRequestBody), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<DeleteProvinceResponseBody> deleteProvince(@PathVariable int id) {
    return new ResponseEntity<>(provinceService.removeProvince(id), HttpStatus.OK);
  }

  @PostMapping("/import")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<String> importProvinces(@RequestParam MultipartFile file) throws CsvValidationException, IOException {
      provinceService.importProvincesFromCSV(file);
      return new ResponseEntity<>("File imported successfully", HttpStatus.OK);
  }
}
