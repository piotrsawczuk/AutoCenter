package sawczuk.AutoCenter.carqueryapi.model.restbody;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiRequest {
    @JsonProperty("model_id")
    private Long modelId;
    @JsonProperty("model_make_id")
    private String makeName;
    @JsonProperty("model_name")
    private String modelName;
    @JsonProperty("model_year")
    private Integer year;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
