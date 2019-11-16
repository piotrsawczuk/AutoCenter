package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Make {
    @JsonProperty("make_id")
    private String id;
    @JsonProperty("make_display")
    private String name;
    @JsonProperty("make_country")
    private String country;
}
