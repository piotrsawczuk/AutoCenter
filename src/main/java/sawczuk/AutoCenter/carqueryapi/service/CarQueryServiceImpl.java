package sawczuk.AutoCenter.carqueryapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.carqueryapi.api.CarQueryApi;
import sawczuk.AutoCenter.carqueryapi.model.*;
import sawczuk.AutoCenter.exception.ErrorMessages;
import sawczuk.AutoCenter.exception.InvalidRequestParametersException;
import sawczuk.AutoCenter.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CarQueryServiceImpl implements CarQueryService {

    @Autowired
    CarQueryApi carQueryApi;

    @Override
    public Trim getModel(Long modelId) {
        if (modelId == null) {
            throw new InvalidRequestParametersException(ErrorMessages.INVALID_REQUEST_PARAMETER);
        }
        Trim trim = carQueryApi.getModel(modelId);
        if (trim.getModelId() == null || trim.getModelId().doubleValue() != modelId) {
            throw new ResourceNotFoundException(ErrorMessages.RESOURCE_NOT_FOUND);
        }
        return trim;
    }

    @Override
    public Year getYears() {
        Year year = carQueryApi.getYears();
        if (year == null) {
            throw new ResourceNotFoundException(ErrorMessages.RESOURCE_NOT_FOUND);
        }
        return year;
    }

    @Override
    public List<Make> getMakes() {
        List<Make> makes = carQueryApi.getMakes();
        if (makes == null || makes.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessages.RESOURCE_NOT_FOUND);
        }
        return makes;
    }

    @Override
    public List<Make> getMakesByYear(Integer year) {
        if (year == null) {
            throw new InvalidRequestParametersException(ErrorMessages.INVALID_REQUEST_PARAMETER);
        }
        List<Make> makes = carQueryApi.getMakesByYear(year);
        if (makes == null || makes.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessages.RESOURCE_NOT_FOUND);
        }
        return makes;
    }

    @Override
    public List<Model> getModelsByYearAndMake(Integer year, String makeName) {
        if (year == null || StringUtils.isEmpty(makeName)) {
            throw new InvalidRequestParametersException(ErrorMessages.INVALID_REQUEST_PARAMETER);
        }
        List<Model> models = carQueryApi.getModelsByYearAndMake(year, makeName);
        if (models == null || models.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessages.RESOURCE_NOT_FOUND);
        }
        return models;
    }

    @Override
    public List<Trim> getTrimsByYearAndMakeAndModel(Integer year, String makeName, String modelName) {
        if (year == null || StringUtils.isEmpty(makeName) || StringUtils.isEmpty(modelName)) {
            throw new InvalidRequestParametersException(ErrorMessages.INVALID_REQUEST_PARAMETER);
        }
        List<Trim> trims = carQueryApi.getTrimsByYearAndMakeAndModel(year, makeName, modelName);
        if (trims == null || trims.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessages.RESOURCE_NOT_FOUND);
        }
        return trims;
    }

    @Override
    public List<TrimBasic> getTrimsBasicByYearAndMakeAndModel(Integer year, String makeName, String modelName) {
        if (year == null || StringUtils.isEmpty(makeName) || StringUtils.isEmpty(modelName)) {
            throw new InvalidRequestParametersException(ErrorMessages.INVALID_REQUEST_PARAMETER);
        }
        List<TrimBasic> trims = carQueryApi.getTrimsBasicByYearAndMakeAndModel(year, makeName, modelName);
        if (trims == null || trims.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessages.RESOURCE_NOT_FOUND);
        }
        return trims;
    }
}
