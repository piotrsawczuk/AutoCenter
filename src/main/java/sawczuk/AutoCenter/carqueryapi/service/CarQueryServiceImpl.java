package sawczuk.AutoCenter.carqueryapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.carqueryapi.api.CarQueryApi;
import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;
import sawczuk.AutoCenter.exception.InvalidRequestParameterException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CarQueryServiceImpl implements CarQueryService {

    @Autowired
    CarQueryApi carQueryApi;

    @Override
    public Year getYears() {
        Year year = carQueryApi.getYears();
        if (year == null) {
            throw new ResourceNotFoundException(Year.class);
        }
        return year;
    }

    @Override
    public List<Make> getMakes() {
        List<Make> makes = carQueryApi.getMakes();
        if (makes == null || makes.isEmpty()) {
            throw new ResourceNotFoundException(Make.class);
        }
        return makes;
    }

    @Override
    public List<Make> getMakesByYear(Integer year) {
        if (year == null) {
            throw new InvalidRequestParameterException("year", year);
        }
        List<Make> makes = carQueryApi.getMakesByYear(year);
        if (makes == null || makes.isEmpty()) {
            throw new ResourceNotFoundException(Make.class, "year", year);
        }
        return makes;
    }

    @Override
    public List<Model> getModelsByMake(String make) {
        if (StringUtils.isEmpty(make)) {
            throw new InvalidRequestParameterException("make", make);
        }
        List<Model> models = carQueryApi.getModelsByMake(make);
        if (models == null || models.isEmpty()) {
            throw new ResourceNotFoundException(Model.class, "make", make);
        }
        return models;
    }

    @Override
    public List<Model> getModelsByMakeAndYear(String make, Integer year) {
        if (year == null || StringUtils.isEmpty(make)) {
            throw new InvalidRequestParameterException("make", make, "year", year);
        }
        List<Model> models = carQueryApi.getModelsByMakeAndYear(make, year);
        if (models == null || models.isEmpty()) {
            throw new ResourceNotFoundException(Model.class, "make", make, "year", year);
        }
        return models;
    }

    @Override
    public List<Trim> getTrims(String make, String model, Integer year) {
        List<Trim> trims = carQueryApi.getTrims(make, model, year);
        if (trims == null || trims.isEmpty()) {
            throw new ResourceNotFoundException(Trim.class, "make", make, "model", model, "year", year);
        }
        return trims;
    }


    @Override
    public Trim getTrim(Long id) {
        if (id == null) {
            throw new InvalidRequestParameterException("id", id);
        }
        Trim trim = carQueryApi.getTrim(id);
        if (trim.getModelId() == null || trim.getModelId().doubleValue() != id) {
            throw new ResourceNotFoundException(Trim.class, "id", id);
        }
        return trim;
    }

}

//TODO Umiescić każde zapytanie we wspólnej metodzie przyjmującej różne parametry?