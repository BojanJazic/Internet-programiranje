package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.entities.ManufacturerEntity;
import etf.unibl.org.ip.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/manufacturers")
public class ManufacturerEntityController {


    private final ManufacturerService manufacturerService;


    public ManufacturerEntityController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<ManufacturerEntity> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }

    @GetMapping("/byId")
    public ManufacturerEntity getManufacturerById(@RequestParam int id) {
        return manufacturerService.getManufacturerById(id);
    }


    @PostMapping
    public ResponseEntity<HttpStatus> addManufacturer(@RequestBody ManufacturerEntity manufacturerEntity) {
        return manufacturerService.addManufacturer(manufacturerEntity);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateManufacturer(@RequestBody ManufacturerEntity manufacturerEntity) {
        return manufacturerService.updateManufacturer(manufacturerEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteManufacturer(@PathVariable int id) {
        return manufacturerService.deleteManufacturer(id);
    }

}
