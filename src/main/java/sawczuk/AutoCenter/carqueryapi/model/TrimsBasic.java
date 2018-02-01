package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrimsBasic {
    @JsonProperty("Trims")
    private List<TrimBasic> trimBasicList;

    public List<TrimBasic> getTrimBasicList() {
        return trimBasicList;
    }

    public void setTrimBasicList(List<TrimBasic> trimBasicList) {
        this.trimBasicList = trimBasicList;
    }
}
