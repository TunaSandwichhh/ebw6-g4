package it.epicode.energy.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.epicode.energy.entities.County;

import it.epicode.energy.entities.Province;
import it.epicode.energy.repositories.CountyRepository;
import it.epicode.energy.repositories.ProvinceRepository;
import it.epicode.energy.types.requests.create.CreateCountyRequestBody;

import it.epicode.energy.types.requests.update.UpdateCountyRequestBody;
import it.epicode.energy.types.responses.DeleteCountyResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Province provinceToFind = provinceRepository.findById(countyRequestBody.getProvinceId()).orElseThrow(() -> new RuntimeException("Province not found with id: " + countyRequestBody.getProvinceId()));

        County countyToCreate = new County();
        countyToCreate.setProvince(provinceToFind);
        setCountyFields(countyToCreate, countyRequestBody);
        return countyRepository.save(countyToCreate);
    }

    public County editCounty(Integer countyId, UpdateCountyRequestBody countyRequestBody) {
        County countyToUpdate = countyRepository.findById(countyId).orElseThrow(() -> new RuntimeException("County not found with id: " + countyId));
       if(countyRequestBody.getProvinceId() > 0 ){
           Province provinceToFind = provinceRepository.findById(countyRequestBody.getProvinceId()).orElseThrow(() -> new RuntimeException("Province not found with id: " + countyRequestBody.getProvinceId()));
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
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {

                String[] actualValues = values[0].split(";");

                County county = new County();
                county.setCountyNumber(Integer.parseInt(actualValues[1]));
                county.setCountyName(actualValues[2]);

                Province province = provinceRepository.findById(2)
                        .orElseThrow(() -> new RuntimeException("Province not found"));

                county.setProvinceName(actualValues[3]);
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

    }

    private void setCountyFields(County countyToCreate, CreateCountyRequestBody countyRequestBody) {
        countyToCreate.setCountyName(countyRequestBody.getCountyName());
    }

    private void setCountyFieldsForDeletion(County countyToCreate, County countyRequestBody) {
        countyToCreate.setCountyNumber(countyRequestBody.getCountyNumber());
        countyToCreate.setCountyName(countyRequestBody.getCountyName());
        countyToCreate.setProvince(countyRequestBody.getProvince());
    }
}