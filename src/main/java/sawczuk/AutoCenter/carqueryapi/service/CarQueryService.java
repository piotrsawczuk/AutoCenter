package sawczuk.AutoCenter.carqueryapi.service;

import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;

import java.util.List;

public interface CarQueryService {
    Year getYears();

    List<Make> getMakes();

    List<Make> getMakesByYear(Integer year);

    List<Model> getModelsByMake(String make);

    List<Model> getModelsByMakeAndYear(String make, Integer year);

    List<Trim> getTrims(String make, String model, Integer year);

    Trim getTrim(Long id);
}
