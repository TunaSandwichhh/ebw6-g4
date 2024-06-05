package it.epicode.energy.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.epicode.energy.entities.County;

import it.epicode.energy.entities.Province;
import it.epicode.energy.entities.enums.CustomerType;
import it.epicode.energy.repositories.CountyRepository;
import it.epicode.energy.repositories.ProvinceRepository;
import it.epicode.energy.types.requests.create.CreateCountyRequestBody;

import it.epicode.energy.types.requests.update.UpdateCountyRequestBody;
import it.epicode.energy.types.responses.DeleteCountyResponseBody;
import it.epicode.energy.types.responses.DeleteCustomerResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class CountyService {

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    public Page<County> retrieveAllCounties(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return countyRepository.findAll(pageable);
    }

    public County retrieveCountyById(Integer countyId) {
        return countyRepository.findById(countyId).orElseThrow(() -> new RuntimeException("County not found with id: " + countyId));
    }

    public County addCounty(CreateCountyRequestBody countyRequestBody) {
        Province provinceToFind = provinceRepository.findById(countyRequestBody.getProvinceName()).orElseThrow(() -> new RuntimeException("Province not found with id: " + countyRequestBody.getProvinceName()));

        County countyToCreate = new County();
        countyToCreate.setProvince(provinceToFind);
        setCountyFields(countyToCreate, countyRequestBody);
        return countyRepository.save(countyToCreate);
    }

    public County editCounty(Integer countyId, UpdateCountyRequestBody countyRequestBody) {
        County countyToUpdate = countyRepository.findById(countyId).orElseThrow(() -> new RuntimeException("County not found with id: " + countyId));
       if(countyRequestBody.getProvinceName() != null ){
           Province provinceToFind = provinceRepository.findById(countyRequestBody.getProvinceName()).orElseThrow(() -> new RuntimeException("Province not found with id: " + countyRequestBody.getProvinceName()));
           countyToUpdate.setProvince(provinceToFind);
       }
        updateCountyFields(countyToUpdate, countyRequestBody);
        return countyRepository.save(countyToUpdate);
    }

    public DeleteCountyResponseBody removeCounty(int countyId) {
        County countyToDelete = countyRepository.findById(countyId).orElseThrow(() -> new RuntimeException("County not found with id: " + countyId));
        County countyToShow = new County();
        setCountyFieldsForDeletion(countyToShow, countyToDelete);
        countyRepository.delete(countyToDelete);
        return new DeleteCountyResponseBody("County deleted successfully", countyToShow);
    }

    public void importCountiesFromCSV(MultipartFile file) throws IOException, CsvValidationException {
        List<County> counties = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] values;
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                County county = new County();
                county.setProvinceCode(Integer.parseInt(values[0]));
                county.setCountyNumber(Integer.parseInt(values[1]));
                county.setCountyName(values[2]);

                Province province = provinceRepository.findById(values[3])
                        .orElseThrow(() -> new RuntimeException("Province not found"));
                county.setProvince(province);

                counties.add(county);
            }
        }
        countyRepository.saveAll(counties);
    }


    private void updateCountyFields(County countyToUpdate, UpdateCountyRequestBody countyRequestBody) {

        if (countyRequestBody.getCountyName() != null) {
            countyToUpdate.setCountyName(countyRequestBody.getCountyName());
        }
        if (countyRequestBody.getProvinceCode() >0) {
            countyToUpdate.setProvinceCode(countyRequestBody.getProvinceCode());
        }

    }

    private void setCountyFields(County countyToCreate, CreateCountyRequestBody countyRequestBody) {
        countyToCreate.setCountyName(countyRequestBody.getCountyName());
        countyToCreate.setProvinceCode(countyRequestBody.getProvinceCode());

    }

    private void setCountyFieldsForDeletion(County countyToCreate, County countyRequestBody) {
        countyToCreate.setCountyNumber(countyRequestBody.getCountyNumber());
        countyToCreate.setCountyName(countyRequestBody.getCountyName());
        countyToCreate.setProvinceCode(countyRequestBody.getProvinceCode());
        countyToCreate.setProvince(countyRequestBody.getProvince());
    }
}