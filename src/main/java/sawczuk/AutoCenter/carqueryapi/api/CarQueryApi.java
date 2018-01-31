package sawczuk.AutoCenter.carqueryapi.api;

import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;

import java.util.List;

public interface CarQueryApi {
    Trim getModel(Long modelId) ;

    Year getYears();

    List<Make> getMakes();

    List<Make> getMakesByYear(Integer year);

    List<Model> getModelsByYearAndMake(Integer year, String makeName);

    List<Trim> getTrimsByYearAndMakeAndModel(Integer year, String makeName, String modelName);
}
