package sawczuk.AutoCenter.carqueryapi.api;

import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import sawczuk.AutoCenter.carqueryapi.model.*;

import java.util.Arrays;
import java.util.List;

@Repository
public class CarQueryApiImpl implements CarQueryApi {

    @Override
    public Trim getModel(Long modelId) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getModel&model=" + modelId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Trim[]> respEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), Trim[].class);
        return respEntity.getBody()[0];
    }

    @Override
    public Year getYears() {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getYears";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Years> respEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), Years.class);
        return respEntity.getBody().getYear();

    }

    @Override
    public List<Make> getMakes() {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getMakes";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Makes> respEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), Makes.class);
        return respEntity.getBody().getMakeList();
    }

    @Override
    public List<Make> getMakesByYear(Integer year) {
        String parameters = "";
        parameters += "&year=" + year;
        return getMakes(parameters);
    }

    @Override
    public List<Model> getModelsByYearAndMake(Integer year, String makeName) {
        String parameters = "";
        parameters += "&year=" + year;
        parameters += "&make=" + makeName;
        return getModels(parameters);
    }

    @Override
    public List<Trim> getTrimsByYearAndMakeAndModel(Integer year, String makeName, String modelName) {
        String parameters = "";
        parameters += "&year=" + year;
        parameters += "&make=" + makeName;
        parameters += "&model=" + modelName;
        return getTrims(parameters);
    }

    @Override
    public List<TrimBasic> getTrimsBasicByYearAndMakeAndModel(Integer year, String makeName, String modelName) {
        String parameters = "";
        parameters += "&year=" + year;
        parameters += "&make=" + makeName;
        parameters += "&model=" + modelName;
        return getTrimsBasic(parameters);
    }


    private List<Make> getMakes(String parameters) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getMakes";
        url += parameters;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Makes> respEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), Makes.class);
        return respEntity.getBody().getMakeList();
    }

    private List<Model> getModels(String parameters) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getModels";
        url += parameters;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Models> respEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), Models.class);
        return respEntity.getBody().getModelList();
    }

    private List<Trim> getTrims(String parameters) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getTrims";
        url += parameters;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Trims> respEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), Trims.class);
        return respEntity.getBody().getTrimList();
    }

    private List<TrimBasic> getTrimsBasic(String parameters) {
        String url = "https://www.carqueryapi.com/api/0.3/?cmd=getTrims&full_results=0";
        url += parameters;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TrimsBasic> respEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), TrimsBasic.class);
        return respEntity.getBody().getTrimBasicList();
    }


    private HttpEntity<String> httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);
        return httpEntity;
    }
}
