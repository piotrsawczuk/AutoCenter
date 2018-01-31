package sawczuk.AutoCenter.carqueryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;
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
        if (apiRequest.getModelId() == null) {
            return new ResponseEntity<Trim>(HttpStatus.BAD_REQUEST);
        }
        Trim model = carQueryService.getModel(apiRequest.getModelId());
        if (model == null) {
            return new ResponseEntity<Trim>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Trim>(model, HttpStatus.OK);
    }

    @RequestMapping(value = "/years", method = RequestMethod.GET)
    public ResponseEntity<Year> getYears() {
        Year year = carQueryService.getYears();
        if (year == null) {
            return new ResponseEntity<Year>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Year>(year, HttpStatus.OK);
    }

    @RequestMapping(value = "/makes", method = RequestMethod.GET)
    public ResponseEntity<List<Make>> getMakes() {
        List<Make> makes = carQueryService.getMakes();
        if (makes == null || makes.isEmpty()) {
            return new ResponseEntity<List<Make>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Make>>(makes, HttpStatus.OK);
    }

    @RequestMapping(value = "/makes", method = RequestMethod.POST)
    public ResponseEntity<List<Make>> getMakesByYear(@RequestBody ApiRequest apiRequest) {
        if (apiRequest.getYear() == null) {
            return new ResponseEntity<List<Make>>(HttpStatus.BAD_REQUEST);
        }
        List<Make> makes = carQueryService.getMakesByYear(apiRequest.getYear());
        if (makes == null || makes.isEmpty()) {
            return new ResponseEntity<List<Make>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Make>>(makes, HttpStatus.OK);
    }

    @RequestMapping(value = "/models", method = RequestMethod.POST)
    public ResponseEntity<List<Model>> getModelsByYearAndMake(@RequestBody ApiRequest apiRequest) {
        if (apiRequest.getYear() == null || apiRequest.getMakeName() == null) {
            return new ResponseEntity<List<Model>>(HttpStatus.BAD_REQUEST);
        }
        List<Model> models = carQueryService.getModelsByYearAndMake(apiRequest.getYear(), apiRequest.getMakeName());
        if (models == null || models.isEmpty()) {
            return new ResponseEntity<List<Model>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Model>>(models, HttpStatus.OK);
    }

    @RequestMapping(value = "/trims", method = RequestMethod.POST)
    public ResponseEntity<List<Trim>> getTrimsByYearAndMakeAndModel(@RequestBody ApiRequest apiRequest) {
        if (apiRequest.getYear() == null || apiRequest.getMakeName() == null || apiRequest.getModelName() == null) {
            return new ResponseEntity<List<Trim>>(HttpStatus.BAD_REQUEST);
        }
        List<Trim> trims = carQueryService.getTrimsByYearAndMakeAndModel(apiRequest.getYear(), apiRequest.getMakeName(), apiRequest.getModelName());
        if (trims == null || trims.isEmpty()) {
            return new ResponseEntity<List<Trim>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Trim>>(trims, HttpStatus.OK);
    }

}
