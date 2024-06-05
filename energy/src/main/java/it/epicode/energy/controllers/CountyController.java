package it.epicode.energy.controllers;
import it.epicode.energy.entities.County;
import it.epicode.energy.services.CountyService;
import it.epicode.energy.types.requests.create.CreateCountyRequestBody;
import it.epicode.energy.types.requests.update.UpdateCountyRequestBody;
import it.epicode.energy.types.responses.DeleteCountyResponseBody;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/counties")
public class CountyController {

    @Autowired
    private CountyService countyService;

    @GetMapping
    public ResponseEntity<Page<County>> getCounties(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(countyService.retrieveAllCounties(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<County> getCounty(@PathVariable int id){
        return new ResponseEntity<>(countyService.retrieveCountyById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<County> createCounty(@RequestBody @Validated CreateCountyRequestBody countyRequestBody, BindingResult validation) throws BadRequestException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc+curr));
        }
        return new ResponseEntity<>(countyService.addCounty(countyRequestBody), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<County> updateCounty(@RequestBody @Validated UpdateCountyRequestBody countyRequestBody, @PathVariable int id, BindingResult validation) throws BadRequestException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc+curr));
        }
        return new ResponseEntity<>(countyService.editCounty(id, countyRequestBody), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCountyResponseBody> deleteCounty(@PathVariable int id){
        return new ResponseEntity<>(countyService.removeCounty(id), HttpStatus.OK);
    }

}
