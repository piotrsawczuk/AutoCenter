package sawczuk.AutoCenter.carqueryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sawczuk.AutoCenter.carqueryapi.model.*;
import sawczuk.AutoCenter.carqueryapi.model.restbody.ApiRequest;
import sawczuk.AutoCenter.carqueryapi.service.CarQueryService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CarQueryController {

    @Autowired
    CarQueryService carQueryService;

    @RequestMapping(value = "/model", method = RequestMethod.POST)
    public ResponseEntity<Trim> getModel(@RequestBody ApiRequest apiRequest) {
        Trim model = carQueryService.getModel(apiRequest.getModelId());
        return new ResponseEntity<Trim>(model, HttpStatus.OK);
    }

    @RequestMapping(value = "/years", method = RequestMethod.GET)
    public ResponseEntity<Year> getYears() {
        Year year = carQueryService.getYears();
        return new ResponseEntity<Year>(year, HttpStatus.OK);
    }

    @RequestMapping(value = "/makes", method = RequestMethod.GET)
    public ResponseEntity<List<Make>> getMakes() {
        List<Make> makes = carQueryService.getMakes();
        return new ResponseEntity<List<Make>>(makes, HttpStatus.OK);
    }

    @RequestMapping(value = "/makes", method = RequestMethod.POST)
    public ResponseEntity<List<Make>> getMakesByYear(@RequestBody ApiRequest apiRequest) {
        List<Make> makes = carQueryService.getMakesByYear(apiRequest.getYear());
        return new ResponseEntity<List<Make>>(makes, HttpStatus.OK);
    }

    @RequestMapping(value = "/models", method = RequestMethod.POST)
    public ResponseEntity<List<Model>> getModelsByYearAndMake(@RequestBody ApiRequest apiRequest) {
        List<Model> models = carQueryService.getModelsByYearAndMake(apiRequest.getYear(), apiRequest.getMakeName());
        return new ResponseEntity<List<Model>>(models, HttpStatus.OK);
    }

    @RequestMapping(value = "/trims", method = RequestMethod.POST)
    public ResponseEntity<List<Trim>> getTrimsByYearAndMakeAndModel(@RequestBody ApiRequest apiRequest) {
        List<Trim> trims = carQueryService.getTrimsByYearAndMakeAndModel(apiRequest.getYear(), apiRequest.getMakeName(), apiRequest.getModelName());
        return new ResponseEntity<List<Trim>>(trims, HttpStatus.OK);
    }

    @RequestMapping(value = "/trimsBasic", method = RequestMethod.POST)
    public ResponseEntity<List<TrimBasic>> getTrimsBasicByYearAndMakeAndModel(@RequestBody ApiRequest apiRequest) {
        List<TrimBasic> trims = carQueryService.getTrimsBasicByYearAndMakeAndModel(apiRequest.getYear(), apiRequest.getMakeName(), apiRequest.getModelName());
        return new ResponseEntity<List<TrimBasic>>(trims, HttpStatus.OK);
    }


}
