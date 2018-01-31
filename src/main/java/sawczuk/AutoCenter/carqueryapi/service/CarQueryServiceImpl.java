package sawczuk.AutoCenter.carqueryapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sawczuk.AutoCenter.carqueryapi.api.CarQueryApi;
import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;

import java.util.List;

@Service
public class CarQueryServiceImpl implements CarQueryService{

    @Autowired
    CarQueryApi carQueryApi;

    @Override
    public Trim getModel(Long modelId) {
        Trim trim = carQueryApi.getModel(modelId);
        if (trim.getModelId() != null && trim.getModelId().doubleValue() == modelId){
            return trim;
        }
        return null;
    }

    @Override
    public Year getYears() {
        return carQueryApi.getYears();
    }

    @Override
    public List<Make> getMakes() {
        return carQueryApi.getMakes();
    }

    @Override
    public List<Make> getMakesByYear(Integer year) {
        return carQueryApi.getMakesByYear(year);
    }

    @Override
    public List<Model> getModelsByYearAndMake(Integer year, String makeName) {
        return carQueryApi.getModelsByYearAndMake(year, makeName);
    }

    @Override
    public List<Trim> getTrimsByYearAndMakeAndModel(Integer year, String makeName, String modelName) {
        return carQueryApi.getTrimsByYearAndMakeAndModel(year, makeName, modelName);
    }
}
