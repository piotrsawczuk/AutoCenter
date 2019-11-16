package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model {
    @JsonProperty("model_name")
    private String name;
    @JsonProperty("model_make_id")
    private String makeId;
}
