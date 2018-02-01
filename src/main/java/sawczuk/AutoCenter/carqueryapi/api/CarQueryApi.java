package sawczuk.AutoCenter.carqueryapi.api;

import sawczuk.AutoCenter.carqueryapi.model.*;

import java.util.List;

public interface CarQueryApi {
    Trim getModel(Long modelId);

    Year getYears();

    List<Make> getMakes();

    List<Make> getMakesByYear(Integer year);

    List<Model> getModelsByYearAndMake(Integer year, String makeName);

    List<Trim> getTrimsByYearAndMakeAndModel(Integer year, String makeName, String modelName);

    List<TrimBasic> getTrimsBasicByYearAndMakeAndModel(Integer year, String makeName, String modelName);
}
