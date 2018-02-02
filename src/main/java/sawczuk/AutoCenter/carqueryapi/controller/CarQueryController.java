package sawczuk.AutoCenter.carqueryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sawczuk.AutoCenter.carqueryapi.model.Make;
import sawczuk.AutoCenter.carqueryapi.model.Model;
import sawczuk.AutoCenter.carqueryapi.model.Trim;
import sawczuk.AutoCenter.carqueryapi.model.Year;
import sawczuk.AutoCenter.carqueryapi.service.CarQueryService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/carqueryapi")
public class CarQueryController {

    @Autowired
    CarQueryService carQueryService;

    @RequestMapping(value = "/year", method = RequestMethod.GET)
    public ResponseEntity<Year> getYears() {
        Year year = carQueryService.getYears();
        return new ResponseEntity<Year>(year, HttpStatus.OK);
    }

    @RequestMapping(value = "/make", method = RequestMethod.GET)
    public ResponseEntity<List<Make>> getMakes(@RequestParam(value = "year", required = false) Integer year) {
        List<Make> makes;
        if (year == null)
            makes = carQueryService.getMakes();
        else
            makes = carQueryService.getMakesByYear(year);
        return new ResponseEntity<List<Make>>(makes, HttpStatus.OK);
    }

    @RequestMapping(value = "/model", method = RequestMethod.GET)
    public ResponseEntity<List<Model>> getModelsByYearAndMake(
            @RequestParam(value = "make") String make,
            @RequestParam(value = "year", required = false) Integer year) {
        List<Model> models;
        if (year == null)
            models = carQueryService.getModelsByMake(make);
        else
            models = carQueryService.getModelsByMakeAndYear(make, year);
        return new ResponseEntity<List<Model>>(models, HttpStatus.OK);
    }

    @RequestMapping(value = "/trim", method = RequestMethod.GET)
    public ResponseEntity<List<Trim>> getTrimsByYearAndMakeAndModel(
            @RequestParam(value = "make", required = false) String make,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "year", required = false) Integer year) {
        List<Trim> trims = carQueryService.getTrims(make, model, year);
        return new ResponseEntity<List<Trim>>(trims, HttpStatus.OK);
    }

    @RequestMapping(value = "/trim/{id}", method = RequestMethod.GET)
    public ResponseEntity<Trim> getTrim(@PathVariable("id") Long id) {
        Trim model = carQueryService.getTrim(id);
        return new ResponseEntity<Trim>(model, HttpStatus.OK);
    }

}