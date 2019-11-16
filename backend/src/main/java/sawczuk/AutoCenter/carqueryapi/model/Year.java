package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Year {
    @JsonProperty("min_year")
    private Integer minYear;
    @JsonProperty("max_year")
    private Integer maxYear;
}
