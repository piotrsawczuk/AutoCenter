package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Model {
    Long id;
    @JsonProperty("model_name")
    String name;
    Long makeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMakeId() {
        return makeId;
    }

    public void setMakeId(Long makeId) {
        this.makeId = makeId;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", makeId=" + makeId +
                '}';
    }
}
