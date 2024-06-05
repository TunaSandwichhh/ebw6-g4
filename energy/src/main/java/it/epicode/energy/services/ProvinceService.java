package it.epicode.energy.services;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import it.epicode.energy.entities.Province;
import it.epicode.energy.repositories.ProvinceRepository;
import it.epicode.energy.types.requests.create.CreateProvinceRequestBody;
import it.epicode.energy.types.requests.update.UpdateProvinceRequestBody;
import it.epicode.energy.types.responses.DeleteProvinceResponseBody;
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
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public Page<Province> retrieveAllProvinces(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return provinceRepository.findAll(pageable);
    }

    public Province retrieveProvinceById(String provinceName){
        return provinceRepository.findById(provinceName).orElseThrow(()-> new RuntimeException("Province not found with name: " + provinceName));
    }

    public Province addProvince(CreateProvinceRequestBody provinceRequestBody){
        Province provinceToCreate = new Province();
        setProvinceFields(provinceToCreate, provinceRequestBody);

        return provinceRepository.save(provinceToCreate);
    }

    public Province editProvince(String provinceName, UpdateProvinceRequestBody provinceRequestBody){
        Province provinceToUpdate = provinceRepository.findById(provinceName).orElseThrow(()-> new RuntimeException("Province not found with name:" + provinceName));
        updateProvinceFields(provinceToUpdate, provinceRequestBody);
        return provinceRepository.save(provinceToUpdate);
    }

    public DeleteProvinceResponseBody removeProvince(String provinceName) {
        Province provinceToDelete = provinceRepository.findById(provinceName).orElseThrow(()-> new RuntimeException("Province not found with name:" + provinceName));

        Province provinceToShow = new Province();
        setProvinceFieldsForDeletion(provinceToShow, provinceToDelete);
        provinceRepository.delete(provinceToDelete);
        return new DeleteProvinceResponseBody("Province deleted successfully", provinceToShow);
    }

    public void importProvincesFromCSV(MultipartFile file) throws IOException, CsvValidationException {
        List<Province> provinces = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] values;
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                Province province = new Province();
                province.setInitials(values[0]);
                province.setProvinceName(values[1]);
                province.setRegion(values[2]);
                provinces.add(province);
            }
        }
        provinceRepository.saveAll(provinces);
    }

    /**
     * HELPER
     * @param provinceToUpdate
     * @param provinceRequestBody
     */
    public void updateProvinceFields(Province provinceToUpdate, UpdateProvinceRequestBody provinceRequestBody) {
        if (provinceRequestBody.getInitials() != null) {
            provinceToUpdate.setInitials(provinceRequestBody.getInitials());
        }
        if (provinceRequestBody.getRegion() != null) {
            provinceToUpdate.setRegion(provinceRequestBody.getRegion());
        }
    }

    /**
     * HELPER
     * @param provinceToCreate
     * @param provinceRequestBody
     */

    public void setProvinceFields(Province provinceToCreate, CreateProvinceRequestBody provinceRequestBody) {
        provinceToCreate.setInitials(provinceRequestBody.getInitials());
        provinceToCreate.setRegion(provinceRequestBody.getRegion());

    }

    public void setProvinceFieldsForDeletion(Province provinceToCreate, Province provinceRequestBody) {
        provinceToCreate.setRegion(provinceRequestBody.getRegion());
        provinceToCreate.setInitials(provinceRequestBody.getInitials());
        //va inserito il setter delle County?
        //provinceToCreate.setCounties(provinceRequestBody.getCounties());
    }

}
